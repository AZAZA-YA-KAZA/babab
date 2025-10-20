CREATE TABLE recipe (
    id_recipe SERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    photo_url VARCHAR(255)
);

create table ingredient (
    id_ingredient SERIAL PRIMARY KEY,
    nam VARCHAR(255)
);
insert into ingredient values (1, 'kartoshka');

create table recipe_ingredient (
    id_recipe_ingredient SERIAL PRIMARY KEY,
    id_recipe SERIAL,
    id_ingredient SERIAL,
    count int,
    unit varchar(255),

    -- Внешние ключи с каскадным удалением
    CONSTRAINT fk_recipe
       FOREIGN KEY (id_recipe)
           REFERENCES recipe(id_recipe)
           ON DELETE CASCADE,

    CONSTRAINT fk_ingredient
       FOREIGN KEY (id_ingredient)
           REFERENCES ingredient(id_ingredient)
           ON DELETE CASCADE,

    -- Уникальность комбинации рецепт-ингредиент
    CONSTRAINT unique_recipe_ingredient
       UNIQUE (id_recipe, id_ingredient)
);
create table step (
    id_step SERIAL PRIMARY KEY,
    id_recipe SERIAL,
    step_description TEXT,
    step_order int,

    -- Внешний ключ с каскадным удалением
    CONSTRAINT fk_recipe_step
      FOREIGN KEY (id_recipe)
          REFERENCES recipe(id_recipe)
          ON DELETE CASCADE,

    -- Уникальность порядка шагов в рамках одного рецепта
    CONSTRAINT unique_step_order
      UNIQUE (id_recipe, step_order)
);