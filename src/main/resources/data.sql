-- general practitioner
insert into general_practitioner (id, general_practitioner_id, first_name, last_name, street, zip, city, country, phone_number)
values (001, 'dom-id-gp-001', 'Arnout', 'Stegehuis', 'Vossenstraat 30', '6811JM', 'Arnheim', 'Netherlands', '0663370595'),
       (002, 'dom-id-gp-002', 'Joep', 'Kuipers', 'Zuidzijde 69', '2411RS', 'Bodegraven', 'Netherlands', '5145125251'),
       (003, 'dom-id-gp-003', 'Mathies', 'Wesselink', 'Ondernemingsweg 67', '8251KW', 'Dronten', 'Netherlands', '0680688352'),
       (004, 'dom-id-gp-004', 'Gerrit', 'Merjenburgh', 'Lange Jufferstraat 176', '3512ED', 'Utrecht', 'Netherlands', '0634577354'),
       (005, 'dom-id-gp-005', 'Thies', 'Veenebrugge', 'Verbindingsweg 191', '2061EK', 'Bloemendaal', 'Netherlands', '0652431375');

-- care provider
insert into care_provider (id, care_provider_id, first_name, last_name, street, zip, city, country, phone_number, specialism)
values (001, 'dom-id-cp-001', 'Stijn', 'Boers', 'De Prunus 101', '7701WR', 'Dedemsvaart', 'Netherlands', '0638469429', 'PHYSIOTHERAPY'),
       (002, 'dom-id-cp-002', 'Loesje', 'Terruwe', 'Zorgvrij 152', '1391RM', 'Abcoude', 'Netherlands', '0683667078', 'APOTHECARY'),
       (003, 'dom-id-cp-003', 'Margriet', 'Ranervelt', 'Laan van Meerdervoort 192', '2555BL', 'Den Haag', 'Netherlands', '0688177729', 'APOTHECARY');

-- patient
insert into patient (id, patient_id, first_name, last_name, street, zip, city, country, gender, birth_date, phone_number, email, is_using_app, medical_record_id, general_practitioner_id)
values (001, 'dom-id-pa-001', 'Lotje', 'Oldhof', 'Johan Frisoplantsoen 184', '2751XR', 'Moerkapelle', 'Netherlands', 'FEMALE', '1978-11-28', '0636038499', 'odin88@packiu.com', true, 'dom-id-mr-001', 'dom-id-gp-001'),
       (002, 'dom-id-pa-002', 'Loes', 'Sens', 'Dorperhofweg 5', '8161PD', 'Epe', 'Netherlands', 'FEMALE', '1995-02-14', '0676549954', 'family777@mercantravellers.com', true, 'dom-id-mr-002', 'dom-id-gp-001'),
       (003, 'dom-id-pa-003', 'Noud', 'Vrisau', 'Vlielandlaan 138', '6922EG', 'Duiven', 'Netherlands', 'MALE', '1984-05-25', '0692663244', 'sicego@hobbylegal.com', true, 'dom-id-mr-003', 'dom-id-gp-002'),
       (004, 'dom-id-pa-004', 'Reinier', 'Taalen', 'Professor Kamerlingh Onnesweg 123', '5144NR', 'Waalwijk', 'Netherlands', 'MALE', '1998-04-04', '0672246079', 'rlbdmd@emvil.com', true, 'dom-id-mr-004', 'dom-id-gp-003'),
       (005, 'dom-id-pa-005', 'Anje', 'Nijboer', 'Burgemeester van Heugtenlaan 82', '2911HA', 'Nieuwerkerk Aan Den Ijssel', 'Netherlands', 'FEMALE', '1946-12-06', '0692152165', 'backstorm@rendrone.fun', true, 'dom-id-mr-005', 'dom-id-gp-003'),
       (006, 'dom-id-pa-006', 'Merel', 'Hilberink', 'Spiekerlanden 111', '9407JD', 'Assen', 'Netherlands', 'FEMALE', '1951-08-09', '0665598329', 'mikeg21@dmxs8.com', true, 'dom-id-mr-006', 'dom-id-gp-004'),
       (007, 'dom-id-pa-007', 'Rik', 'Koldewee', 'Alexander Battalaan 175', '6221CB', 'Maastricht', 'Netherlands', 'MALE', '1965-04-18', '0652071117', '4321j@yt-google.com', true, 'dom-id-mr-007', 'dom-id-gp-004'),
       (008, 'dom-id-pa-008', 'Gust', 'Breukers', 'Nico Werkmanstraat 150', '7556LH', 'Hengelo', 'Netherlands', 'MALE', '1944-06-28', '0668539864', 'rinatalove@101livemail.top', true, 'dom-id-mr-008', 'dom-id-gp-004'),
       (009, 'dom-id-pa-009', 'Ruud', 'Meester', 'De Hovenlaan 31', '7325ZC', 'Apeldoorn', 'Netherlands', 'MALE', '1938-05-24', '0658433139', 'zindy@goldinbox.net', true, 'dom-id-mr-009', 'dom-id-gp-005'),
       (010, 'dom-id-pa-010', 'Jeroen', 'Janse', 'Vijverzicht 186', '2912PN', 'Nieuwerkerk Aan Den Ijssel', 'Netherlands', 'MALE', '1968-04-12', '0676460184', 'zindy@goldinbox.net', true, 'dom-id-mr-010', 'dom-id-gp-005'),
       (011, 'dom-id-pa-011', 'Joeri', 'Daggert', 'Franjumbuorsterpaed 156', '9034GZ', 'Marsum', 'Netherlands', 'MALE', '1947-01-26', '0616575362', 'zindy@goldinbox.net', true, 'dom-id-mr-011', 'dom-id-gp-001'),
       (012, 'dom-id-pa-012', 'Saartje', 'Knapen', 'Doctor Ariensstraat 76', '7071AJ', 'Ulft', 'Netherlands', 'FEMALE', '1958-11-28', '0672753960', 'stevelim@yahoo.ca', true, 'dom-id-mr-012', 'dom-id-gp-001'),
       (013, 'dom-id-pa-013', 'Jacolien', 'Nijalnd', 'Bloemenstraat 10', '6663KJ', 'Apeldoorn', 'Netherlands', 'FEMALE', '1970-12-28', '0624573679', 'keutzer@aol.com', true, 'dom-id-mr-013', 'dom-id-gp-001'),
       (014, 'dom-id-pa-014', 'Ard', 'Paules', 'Hazeleger 165', '4874KZ', 'Etten-leur', 'Netherlands', 'MALE', '1987-08-02', '4014190040', 'wilsonpm@yahoo.ca', true, 'dom-id-mr-014', 'dom-id-gp-002'),
       (015, 'dom-id-pa-015', 'Willemien', 'Leemhuis', 'Johan Frisolaan 2', '3258AJ', 'Den Bommel', 'Netherlands', 'FEMALE', '1984-10-24', '0641567568', 'barlow@comcast.net', true, 'dom-id-mr-015', 'dom-id-gp-003'),
       (016, 'dom-id-pa-016', 'Mariet', 'Bokhorst', 'Oldenzaalsedijk 178', '7561PP', 'Deurningen', 'Netherlands', 'FEMALE', '1947-05-11', '7531877875', 'fwitness@optonline.net', true, 'dom-id-mr-016' ,'dom-id-gp-003'),
       (017, 'dom-id-pa-017', 'Bart', 'Tuink', 'Vlasaard 37', '4759AM', 'Noordhoek', 'Netherlands', 'MALE', '1993-03-02', '0624616558', 'jgwang@optonline.net', true, 'dom-id-mr-017', 'dom-id-gp-004'),
       (018, 'dom-id-pa-018', 'Maaike', 'Leeuw', 'Alexander Battalaan 175', '6221CB', 'Maastricht', 'Netherlands', 'FEMALE', '1977-03-26', '0664742542', 'barlow@comcast.net', true, 'dom-id-mr-018', 'dom-id-gp-005'),
       (019, 'dom-id-pa-019', 'Mannes', 'Stokkers', 'De Ruyschlaan 150', '1181PE', 'Amstelveen', 'Netherlands', 'MALE', '1990-10-10', '0674976363', 'scitext@yahoo.ca', true, 'dom-id-mr-019', 'dom-id-gp-005');

-- medical record
insert into medical_record(id, medical_record_id)
values (001, 'dom-id-mr-001'),
       (002, 'dom-id-mr-002'),
       (003, 'dom-id-mr-003'),
       (004, 'dom-id-mr-004'),
       (005, 'dom-id-mr-005'),
       (006, 'dom-id-mr-006'),
       (007, 'dom-id-mr-007'),
       (008, 'dom-id-mr-008'),
       (009, 'dom-id-mr-009'),
       (010, 'dom-id-mr-010'),
       (011, 'dom-id-mr-011'),
       (012, 'dom-id-mr-012'),
       (013, 'dom-id-mr-013'),
       (014, 'dom-id-mr-014'),
       (015, 'dom-id-mr-015'),
       (016, 'dom-id-mr-016'),
       (017, 'dom-id-mr-017'),
       (018, 'dom-id-mr-018'),
       (019, 'dom-id-mr-019');

-- observation
insert into observation(id, observation_id, title, description, date, medical_record_id)
values (001, 'dom-id-obs-001', 'Observation 1', 'This is a description of observation 1', '2022-11-20', 'dom-id-mr-001'),
       (002, 'dom-id-obs-002', 'Observation 2', 'This is a description of observation 2', '2022-11-20', 'dom-id-mr-001'),
       (003, 'dom-id-obs-003', 'Observation 3', 'This is a description of observation 3', '2022-11-20', 'dom-id-mr-001'),
       (004, 'dom-id-obs-004', 'Observation 1', 'This is a description of observation 1', '2022-11-20', 'dom-id-mr-002'),
       (005, 'dom-id-obs-005', 'Observation 2', 'This is a description of observation 2', '2022-11-20', 'dom-id-mr-002'),
       (006, 'dom-id-obs-006', 'Observation 3', 'This is a description of observation 3', '2022-11-20', 'dom-id-mr-002'),
       (007, 'dom-id-obs-007', 'Observation 1', 'This is a description of observation 1', '2022-11-20', 'dom-id-mr-003'),
       (008, 'dom-id-obs-008', 'Observation 2', 'This is a description of observation 2', '2022-11-20', 'dom-id-mr-003'),
       (009, 'dom-id-obs-009', 'Observation 3', 'This is a description of observation 3', '2022-11-20', 'dom-id-mr-003'),
       (010, 'dom-id-obs-010', 'Observation 1', 'This is a description of observation 1', '2022-11-20', 'dom-id-mr-004'),
       (011, 'dom-id-obs-011', 'Observation 2', 'This is a description of observation 2', '2022-11-20', 'dom-id-mr-004'),
       (012, 'dom-id-obs-012', 'Observation 3', 'This is a description of observation 3', '2022-11-20', 'dom-id-mr-004'),
       (013, 'dom-id-obs-013', 'Observation 1', 'This is a description of observation 1', '2022-11-20', 'dom-id-mr-005'),
       (014, 'dom-id-obs-014', 'Observation 2', 'This is a description of observation 2', '2022-11-20', 'dom-id-mr-005'),
       (015, 'dom-id-obs-015', 'Observation 3', 'This is a description of observation 3', '2022-11-20', 'dom-id-mr-005'),
       (016, 'dom-id-obs-016', 'Observation 1', 'This is a description of observation 1', '2022-11-20', 'dom-id-mr-006'),
       (017, 'dom-id-obs-017', 'Observation 2', 'This is a description of observation 2', '2022-11-20', 'dom-id-mr-006'),
       (018, 'dom-id-obs-018', 'Observation 3', 'This is a description of observation 3', '2022-11-20', 'dom-id-mr-006'),
       (019, 'dom-id-obs-019', 'Observation 1', 'This is a description of observation 1', '2022-11-20', 'dom-id-mr-007'),
       (020, 'dom-id-obs-020', 'Observation 2', 'This is a description of observation 2', '2022-11-20', 'dom-id-mr-007'),
       (021, 'dom-id-obs-021', 'Observation 3', 'This is a description of observation 3', '2022-11-20', 'dom-id-mr-007');

-- medication
insert into medication(id, medication_id, title, description, start_date, end_date, medical_record_id)
values (001, 'dom-id-med-001', 'Medication 1', 'This is a description of medication 1', '2022-10-10', '2022-12-30', 'dom-id-mr-001'),
       (002, 'dom-id-med-002', 'Medication 2', 'This is a description of medication 2', '2022-10-10', '2022-12-30', 'dom-id-mr-001'),
       (003, 'dom-id-med-003', 'Medication 3', 'This is a description of medication 3', '2022-10-10', '2022-12-30', 'dom-id-mr-001'),
       (004, 'dom-id-med-004', 'Medication 1', 'This is a description of medication 1', '2022-10-10', '2022-12-30', 'dom-id-mr-002'),
       (005, 'dom-id-med-005', 'Medication 2', 'This is a description of medication 2', '2022-10-10', '2022-12-30', 'dom-id-mr-002'),
       (006, 'dom-id-med-006', 'Medication 3', 'This is a description of medication 3', '2022-10-10', '2022-12-30', 'dom-id-mr-002'),
       (007, 'dom-id-med-007', 'Medication 1', 'This is a description of medication 1', '2022-10-10', '2022-12-30', 'dom-id-mr-003'),
       (008, 'dom-id-med-008', 'Medication 2', 'This is a description of medication 2', '2022-10-10', '2022-12-30', 'dom-id-mr-003'),
       (009, 'dom-id-med-009', 'Medication 3', 'This is a description of medication 3', '2022-10-10', '2022-12-30', 'dom-id-mr-003'),
       (010, 'dom-id-med-010', 'Medication 1', 'This is a description of medication 1', '2022-10-10', '2022-12-30', 'dom-id-mr-004'),
       (011, 'dom-id-med-011', 'Medication 2', 'This is a description of medication 2', '2022-10-10', '2022-12-30', 'dom-id-mr-004'),
       (012, 'dom-id-med-012', 'Medication 3', 'This is a description of medication 3', '2022-10-10', '2022-12-30', 'dom-id-mr-004');

-- intake
insert into intake(id, intake_id, time, amount, unit, medication_id)
values (001, 'dom-id-itk-001', '14:00', 2, 'PILL', 'dom-id-med-001'),
       (002, 'dom-id-itk-002', '18:00', 10, 'MILLIGRAM', 'dom-id-med-001'),
       (003, 'dom-id-itk-003', '22:00', 250, 'MILLILITRE', 'dom-id-med-001'),
       (004, 'dom-id-itk-004', '14:00', 2, 'PILL', 'dom-id-med-002'),
       (005, 'dom-id-itk-005', '18:00', 10, 'MILLIGRAM', 'dom-id-med-002'),
       (006, 'dom-id-itk-006', '22:00', 250, 'MILLILITRE', 'dom-id-med-002'),
       (007, 'dom-id-itk-007', '14:00', 2, 'PILL', 'dom-id-med-003'),
       (008, 'dom-id-itk-008', '18:00', 10, 'MILLIGRAM', 'dom-id-med-003'),
       (009, 'dom-id-itk-009', '22:00', 250, 'MILLILITRE', 'dom-id-med-003'),
       (010, 'dom-id-itk-010', '14:00', 2, 'PILL', 'dom-id-med-004'),
       (011, 'dom-id-itk-011', '18:00', 10, 'MILLIGRAM', 'dom-id-med-004'),
       (012, 'dom-id-itk-012', '22:00', 250, 'MILLILITRE', 'dom-id-med-004');

-- appointment
insert into appointment(id, appointment_id, date, time, reason, patient_id, general_practitioner_id, care_provider_id)
values (001, 'dom-id-apt-001', '2022-12-01', '14:00', 'The reason for the appointment', 'dom-id-pa-001', 'dom-id-gp-001', ''),
       (002, 'dom-id-apt-002', '2022-12-01', '18:00', 'The reason for the appointment', 'dom-id-pa-001', '', 'dom-id-cp-001');

-- diagnosis
insert into diagnosis(id, diagnosis_id, title, diagnosis_type, date_diagnosed, cause, treatment, advice, medical_record_id)
values (001, 'dom-id-dia-001', 'Diagnosis 1', 'HEART', '2022-12-07', 'heart attack', 'heart medication', 'more rest and less exercises','dom-id-mr-001'),
       (002, 'dom-id-dia-002', 'Diagnosis 2', 'BONES', '2022-02-11', 'motor accident', 'operation and cast for 8 weeks', 'lot of rest, only walk with crutches','dom-id-mr-001');