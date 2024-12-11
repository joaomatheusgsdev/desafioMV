-- Create the package specification
CREATE OR REPLACE PACKAGE ClientReportPackage AS
    PROCEDURE GetClientReport;
END ClientReportPackage;
/
CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY ClientReportPackage AS
    PROCEDURE GetClientReport IS
        CURSOR client_cursor IS
SELECT
    c.nome AS "Nome do cliente",
    c.email AS "Email do cliente",
    cpf.cpf AS "CPF",
    cpj.cnpj AS "CNPJ",
    cont.numero_conta AS "Numero da conta bancaria",
    cont.saldo AS "Saldo em conta",
    emp.nome AS "Nome da Empresa",
    emp.cnpj AS "CNPJ da empresa",
    emp.email AS "Email da Empresa",
    COUNT(mov.id) AS "Numero de Movimentacoes"
FROM cliente c
         LEFT JOIN empresa emp ON c.empresa_id = emp.id
         LEFT JOIN cliente_pf cpf ON c.id = cpf.cliente_id
         LEFT JOIN cliente_pj cpj ON c.id = cpj.cliente_id
         JOIN conta cont ON c.id = cont.cliente_id
         LEFT JOIN movimentacao mov ON cont.id = mov.conta_id
WHERE cpf.cliente_id IS NOT NULL OR cpj.cliente_id IS NOT NULL
GROUP BY
    c.nome,
    c.email,
    cpf.cpf,
    cpj.cnpj,
    cont.numero_conta,
    cont.saldo,
    emp.nome,
    emp.cnpj,
    emp.email;
BEGIN
FOR client_record IN client_cursor LOOP
            DBMS_OUTPUT.PUT_LINE('Nome do cliente: ' || client_record."Nome do cliente" ||
                                 ', Email do cliente: ' || client_record."Email do cliente" ||
                                 ', CPF: ' || client_record."CPF" ||
                                 ', CNPJ: ' || client_record."CNPJ" ||
                                 ', Numero da conta bancaria: ' || client_record."Numero da conta bancaria" ||
                                 ', Saldo em conta: ' || client_record."Saldo em conta" ||
                                 ', Nome da Empresa: ' || client_record."Nome da Empresa" ||
                                 ', CNPJ da empresa: ' || client_record."CNPJ da empresa" ||
                                 ', Email da Empresa: ' || client_record."Email da Empresa" ||
                                 ', Numero de Movimentacoes: ' || client_record."Numero de Movimentacoes");
END LOOP;
END GetClientReport;
END ClientReportPackage;

EXEC ClientReportPackage.GetClientReport;