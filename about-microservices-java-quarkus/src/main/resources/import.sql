DROP SEQUENCE IF EXISTS temp_seq;
CREATE SEQUENCE temp_seq;
INSERT INTO cliente (bloqueio, numdocumentoprincipal, metodopagamento, id, nomecliente, nomedocumento, observacao) VALUES (false, 123456, 'Crédito', nextval('temp_seq'), 'Mario Martins', 'RG', 'TESTE TESTE');
INSERT INTO cliente (bloqueio, numdocumentoprincipal, metodopagamento, id, nomecliente, nomedocumento, observacao) VALUES (false, 123456, 'Crédito', nextval('temp_seq'), 'Helena Martins', 'RG', 'TESTE TESTE');
INSERT INTO cliente (bloqueio, numdocumentoprincipal, metodopagamento, id, nomecliente, nomedocumento, observacao) VALUES (true, 123456, 'Crédito', nextval('temp_seq'), 'Amanda Jéssica', 'RG', 'TESTE TESTE');
INSERT INTO cliente (bloqueio, numdocumentoprincipal, metodopagamento, id, nomecliente, nomedocumento, observacao) VALUES (false, 123456, 'PIX', nextval('temp_seq'), 'Paulo Antunes', 'RG', 'Observação');
alter sequence cliente_seq restart with 5;