
CREATE TABLE planostb(

                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(30) NOT NULL,
                         price decimal(8,2) NOT NULL,
                         durationdays SMALLINT NOT NULL
);

CREATE TABLE alunostb (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           dateofbirth date NOT NULL,
                           cpf CHAR(11) NOT NULL,
                           idplain INT NOT NULL,
                           nextpayment date NOT NULL,
                            FOREIGN KEY(idplain) REFERENCES planostb(id)

);

CREATE TABLE pagamentostb (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    idaluno INT NOT NULL,
    amount DECIMAL(8,2) NOT NULL,
    paymentdate DATE NOT NULL,
    FOREIGN KEY(idaluno) REFERENCES alunostb(id)
)
