INSERT INTO ingredient_category (ingredient_name)
VALUES ('Mięso'),
       ('Drób'),
       ('Ryby i owoce morza'),
       ('Warzywa'),
       ('Owoce'),
       ('Nabiał'),
       ('Jaja'),
       ('Tłuszcze i oleje'),
       ('Kasze i ryże'),
       ('Makarony'),
       ('Rośliny strączkowe'),
       ('Orzechy i pestki'),
       ('Przyprawy'),
       ('Zioła świeże'),
       ('Sosy i pasty'),
       ('Słodycze i desery'),
       ('Pieczywo i wypieki')
ON CONFLICT (ingredient_name) DO NOTHING;
