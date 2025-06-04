INSERT INTO ingredient (category_id, ingredient_name)
VALUES
    -- 1. Mięso
    (1, 'Wołowina'),
    (1, 'Wieprzowina'),

    -- 2. Drób
    (2, 'Kurczak'),
    (2, 'Indyk'),

    -- 3. Ryby i owoce morza
    (3, 'Łosoś'),
    (3, 'Tuńczyk'),

    -- 4. Warzywa
    (4, 'Marchew'),
    (4, 'Brokuł'),

    -- 5. Owoce
    (5, 'Jabłko'),
    (5, 'Banan'),

    -- 6. Nabiał
    (6, 'Mleko'),
    (6, 'Ser żółty'),

    -- 7. Jaja
    (7, 'Jajko kurze'),

    -- 8. Tłuszcze i oleje
    (8, 'Oliwa z oliwek'),
    (8, 'Masło klarowane'),

    -- 9. Kasze i ryże
    (9, 'Ryż biały'),
    (9, 'Kasza gryczana'),

    -- 10. Makarony
    (10, 'Makaron spaghetti'),
    (10, 'Makaron penne'),

    -- 11. Rośliny strączkowe
    (11, 'Soczewica czerwona'),
    (11, 'Ciecierzyca'),

    -- 12. Orzechy i pestki
    (12, 'Migdały'),
    (12, 'Orzechy włoskie'),

    -- 13. Przyprawy
    (13, 'Sól'),
    (13, 'Pieprz czarny'),

    -- 14. Zioła świeże
    (14, 'Bazylia'),
    (14, 'Natka pietruszki'),

    -- 15. Sosy i pasty
    (15, 'Ketchup'),
    (15, 'Musztarda'),

    -- 16. Słodycze i desery
    (16, 'Czekolada gorzka'),
    (16, 'Miód'),

    -- 17. Pieczywo i wypieki
    (17, 'Chleb pszenny'),
    (17, 'Bułka kajzerka')
ON CONFLICT (ingredient_name) DO NOTHING;
