CREATE TABLE clientstb (
                           id int auto_increment PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           dateofbirth date NOT NULL,
                           cpf VARCHAR(11) NOT NULL,
                           plain VARCHAR(20) NOT NULL,
                           payment BOOLEAN NOT NULL,
                           idtrainnig int,
                           FOREIGN KEY(idtrainnig) REFERENCES trainigtb(id)

);

CREATE TABLE trainigtb(
                          id int AUTO_INCREMENT primary KEY,
                          trainigtype VARCHAR(20) NOT NULL,
                          exercisesquantity tinyint NOT NULL,
                          daysoftrainnig tinyint NOT NULL,
                          idtrainer int,
                          FOREIGN KEY(idtrainer) REFERENCES trainertb(id)
);


CREATE TABLE trainertb (
                           id int AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           dateofbirth date NOT NULL,
                           cpf VARCHAR(11) NOT NULL

);