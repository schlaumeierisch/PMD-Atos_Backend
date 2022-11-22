-- general practitioner
insert into general_practitioner (id, general_practitioner_id, first_name, last_name, street, zip, city, country, phone_number)
values (111, 'dom-id-gp-111', 'Arnout', 'Stegehuis', 'Vossenstraat 30', '6811JM', 'Arnheim', 'Netherlands', '0663370595'),
       (222, 'dom-id-gp-222', 'Joep', 'Kuipers', 'Zuidzijde 69', '2411RS', 'Bodegraven', 'Netherlands', '5145125251'),
       (333, 'dom-id-gp-333', 'Mathies', 'Wesselink', 'Ondernemingsweg 67', '8251KW', 'Dronten', 'Netherlands', '0680688352'),
       (444, 'dom-id-gp-444', 'Gerrit', 'Merjenburgh', 'Lange Jufferstraat 176', '3512ED', 'Utrecht', 'Netherlands', '0634577354'),
       (555, 'dom-id-gp-555', 'Thies', 'Veenebrugge', 'Verbindingsweg 191', '2061EK', 'Bloemendaal', 'Netherlands', '0652431375');

-- care provider
insert into care_provider (id, care_provider_id, first_name, last_name, street, zip, city, country, phone_number, specialism)
values (111, 'dom-id-cp-111', 'Stijn', 'Boers', 'De Prunus 101', '7701WR', 'Dedemsvaart', 'Netherlands', '0638469429', 'PHYSIOTHERAPY'),
       (222, 'dom-id-cp-222', 'Loesje', 'Terruwe', 'Zorgvrij 152', '1391RM', 'Abcoude', 'Netherlands', '0683667078', 'APOTHECARY'),
       (333, 'dom-id-cp-333', 'Margriet', 'Ranervelt', 'Laan van Meerdervoort 192', '2555BL', 'Den Haag', 'Netherlands', '0688177729', 'APOTHECARY');