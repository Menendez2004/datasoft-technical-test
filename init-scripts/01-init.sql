

CREATE SCHEMA IF NOT EXISTS public;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public.generes (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    full_name VARCHAR(30) NOT NULL,
    username VARCHAR(20) UNIQUE NOT NULL,
    passwd VARCHAR(128) NOT NULL,
    state VARCHAR(3) NOT NULL DEFAULT 'ACT'
);

-- Tabla de libros
CREATE TABLE IF NOT EXISTS public.books (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(50) NOT NULL,
    summary VARCHAR(500),
    price NUMERIC(6,2) NOT NULL,
    state VARCHAR(10) NOT NULL DEFAULT 'AVAILABLE',
    image VARCHAR(500),
    gen_id UUID NOT NULL,
    usr_id UUID NOT NULL,
    
    CONSTRAINT fk_books_genere FOREIGN KEY (gen_id) REFERENCES public.generes(id),
    CONSTRAINT fk_books_user FOREIGN KEY (usr_id) REFERENCES public.users(id)
);

CREATE INDEX IF NOT EXISTS idx_books_gen_id ON public.books(gen_id);
CREATE INDEX IF NOT EXISTS idx_books_usr_id ON public.books(usr_id);
CREATE INDEX IF NOT EXISTS idx_books_state ON public.books(state);
CREATE INDEX IF NOT EXISTS idx_users_username ON public.users(username);

INSERT INTO public.generes (id, name) VALUES 
    (uuid_generate_v4(), 'Ficción'),
    (uuid_generate_v4(), 'Ciencia Ficción'),
    (uuid_generate_v4(), 'Romance'),
    (uuid_generate_v4(), 'Misterio'),
    (uuid_generate_v4(), 'Fantasía'),
    (uuid_generate_v4(), 'Historia'),
    (uuid_generate_v4(), 'Biografía'),
    (uuid_generate_v4(), 'Tecnología')
ON CONFLICT (id) DO NOTHING;

-- Insertar usuarios de ejemplo
INSERT INTO public.users (id, full_name, username, passwd, state) VALUES 
    (uuid_generate_v4(), 'Juan Pérez', 'jperez', '$2a$12$MJ6dLCG7lsIhf3RObKGirutliEg469P3hz8.g2ANKe9zloSzm7f2q', 'ACT'),
    (uuid_generate_v4(), 'María García', 'mgarcia', '$2a$12$MJ6dLCG7lsIhf3RObKGirutliEg469P3hz8.g2ANKe9zloSzm7f2q', 'ACT'),
    (uuid_generate_v4(), 'Carlos López', 'clopez', '$2a$12$MJ6dLCG7lsIhf3RObKGirutliEg469P3hz8.g2ANKe9zloSzm7f2q', 'ACT'),
    (uuid_generate_v4(), 'Ana Martínez', 'amartinez', '$2a$12$MJ6dLCG7lsIhf3RObKGirutliEg469P3hz8.g2ANKe9zloSzm7f2q', 'INA')
ON CONFLICT (id) DO NOTHING;

INSERT INTO public.books (id, name, summary, price, state, image, gen_id, usr_id) 
SELECT 
    uuid_generate_v4(),
    'El Quijote',
    'Las aventuras de Don Quijote de la Mancha',
    25.99,
    'AVAILABLE',
    '/images/quijote.jpg',
    (SELECT id FROM public.generes WHERE name = 'Ficción' LIMIT 1),
    (SELECT id FROM public.users WHERE username = 'jperez' LIMIT 1)
UNION ALL
SELECT 
    uuid_generate_v4(),
    '1984',
    'Una distopía sobre el control totalitario',
    18.50,
    'AVAILABLE',
    '/images/1984.jpg',
    (SELECT id FROM public.generes WHERE name = 'Ciencia Ficción' LIMIT 1),
    (SELECT id FROM public.users WHERE username = 'mgarcia' LIMIT 1)
UNION ALL
SELECT 
    uuid_generate_v4(),
    'Cien años de soledad',
    'La historia de la familia Buendía',
    22.75,
    'AVAILABLE',
    '/images/soledad.jpg',
    (SELECT id FROM public.generes WHERE name = 'Ficción' LIMIT 1),
    (SELECT id FROM public.users WHERE username = 'jperez' LIMIT 1)
UNION ALL
SELECT 
    uuid_generate_v4(),
    'El Señor de los Anillos',
    'La épica aventura en la Tierra Media',
    35.00,
    'SOLD',
    '/images/lotr.jpg',
    (SELECT id FROM public.generes WHERE name = 'Fantasía' LIMIT 1),
    (SELECT id FROM public.users WHERE username = 'clopez' LIMIT 1)
UNION ALL
SELECT 
    uuid_generate_v4(),
    'Clean Code',
    'Mejores prácticas para escribir código limpio',
    45.99,
    'AVAILABLE',
    '/images/cleancode.jpg',
    (SELECT id FROM public.generes WHERE name = 'Tecnología' LIMIT 1),
    (SELECT id FROM public.users WHERE username = 'mgarcia' LIMIT 1)
UNION ALL
SELECT 
    uuid_generate_v4(),
    'Romeo y Julieta',
    'La trágica historia de amor',
    15.25,
    'AVAILABLE',
    '/images/romeo.jpg',
    (SELECT id FROM public.generes WHERE name = 'Romance' LIMIT 1),
    (SELECT id FROM public.users WHERE username = 'clopez' LIMIT 1)
ON CONFLICT (id) DO NOTHING;

SELECT 'Base de datos pruebadatasoft inicializada correctamente' as status;
SELECT 'Géneros creados: ' || COUNT(*) as generes_count FROM public.generes;
SELECT 'Usuarios creados: ' || COUNT(*) as users_count FROM public.users;
SELECT 'Libros creados: ' || COUNT(*) as books_count FROM public.books;
