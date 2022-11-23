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

-- patient
insert into patient (id, patient_id, first_name, last_name, gender, birth_date, phone_number, email, is_using_app, general_practitioner_id)
values (111, 'dom-id-pa-111', 'Lotje', 'Oldhof', 'female', '1978-11-28', '0636038499', 'odin88@packiu.com', true, 111),
       (222, 'dom-id-pa-222', 'Loes', 'Sens', 'female', '1995-02-14', '0676549954', 'family777@mercantravellers.com', true, 111),
       (333, 'dom-id-pa-333', 'Noud', 'Vrisau', 'male', '1984-05-25', '0692663244', 'sicego@hobbylegal.com', true, 222),
       (444, 'dom-id-pa-444', 'Reinier', 'Taalen', 'male', '1998-04-04', '0672246079', 'rlbdmd@emvil.com', true, 333),
       (555, 'dom-id-pa-555', 'Anje', 'Nijboer', 'female', '1946-12-06', '0692152165', 'backstorm@rendrone.fun', true, 333),
       (666, 'dom-id-pa-666', 'Merel', 'Hilberink', 'female', '1951-08-09', '0665598329', 'mikeg21@dmxs8.com', true, 444),
       (777, 'dom-id-pa-777', 'Rik', 'Koldewee', 'male', '1965-04-18', '0652071117', '4321j@yt-google.com', true, 444),
       (888, 'dom-id-pa-888', 'Gust', 'Breukers', 'male', '1944-06-28', '0668539864', 'rinatalove@101livemail.top', true, 444),
       (999, 'dom-id-pa-999', 'Ruud', 'Meester', 'male', '1938-05-24', '0658433139', 'zindy@goldinbox.net', true, 555);
