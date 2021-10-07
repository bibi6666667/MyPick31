drop schema if exists `mypick31`;
create schema if not exists `mypick31` default character set utf8;
use `mypick31`;

drop table if exists `mypick31`.`base_type`;
create table if not exists `mypick31`.`base_type` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    primary key (`id`)
);

drop table if exists `mypick31`.`topping_type`;
create table if not exists `mypick31`.`topping_type` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    primary key (`id`)
);

drop table if exists `mypick31`.`syrup_type`;
create table if not exists `mypick31`.`syrup_type` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    primary key (`id`)
);

drop table if exists `mypick31`.`allergen`;
create table if not exists `mypick31`.`allergen` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    primary key (`id`)
);

-------------

drop table if exists `mypick31`.`base`;
create table if not exists `mypick31`.`base` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `is_sherbet` tinyint(1) not null,
    `is_sorbet` tinyint(1) not null,
    `base_type_id` int not null,
    primary key (`id`),
    foreign key (`base_type_id`) references `base_type` (`id`)
);

drop table if exists `mypick31`.`topping`;
create table if not exists `mypick31`.`topping` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `topping_type_id` int not null,
    primary key (`id`),
    foreign key (`topping_type_id`) references `topping_type` (`id`)
);

drop table if exists `mypick31`.`syrup`;
create table if not exists `mypick31`.`syrup` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `syrup_type_id` int not null,
    primary key (`id`),
    foreign key (`syrup_type_id`) references `syrup_type` (`id`)
);

drop table if exists `mypick31`.`flavor`;
create table if not exists `mypick31`.`flavor` (
    `id` int not null auto_increment,
    `name_kr` varchar(45) not null,
    `name_en` varchar(45) not null,
    `kcal` int not null,
    `is_signature` tinyint(1) not null,
    primary key (`id`)
);

drop table if exists `mypick31`.`info`;
create table if not exists `mypick31`.`info` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `content` varchar(100) not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`)
);

drop table if exists `mypick31`.`image`;
create table if not exists `mypick31`.`image` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `image_address` varchar(100) not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`)
);

drop table if exists `mypick31`.`on_sale`;
create table if not exists `mypick31`.`on_sale` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `is_on_sale` tinyint(1) not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`)
);

------ N:M Tables

drop table if exists `mypick31`.`flavor_base`;
create table if not exists `mypick31`.`flavor_base` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `base_id` int not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`),
    foreign key (`base_id`) references `base` (`id`)
);

drop table if exists `mypick31`.`flavor_topping`;
create table if not exists `mypick31`.`flavor_topping` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `topping_id` int not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`),
    foreign key (`topping_id`) references `topping` (`id`)
);

drop table if exists `mypick31`.`flavor_syrup`;
create table if not exists `mypick31`.`flavor_syrup` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `syrup_id` int not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`),
    foreign key (`syrup_id`) references `syrup` (`id`)
);

drop table if exists `mypick31`.`flavor_allergen`;
create table if not exists `mypick31`.`flavor_allergen` (
    `id` int not null auto_increment,
    `flavor_id` int not null,
    `allergen_id` int not null,
    primary key (`id`),
    foreign key (`flavor_id`) references `flavor` (`id`),
    foreign key (`allergen_id`) references `allergen` (`id`)
);