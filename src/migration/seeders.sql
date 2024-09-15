INSERT INTO clients (name, address, phone, is_professional) VALUES
                                                               ('John Doe', '123 Main St', '555-1234', true),
                                                               ('Jane Smith', '456 Oak Ave', '555-5678', false),
                                                               ('ACME Corp', '789 Pine Rd', '555-9876', true),
                                                               ('Mary Johnson', '101 Elm St', '555-2468', false),
                                                               ('Bob Brown', '202 Cedar Blvd', '555-3691', true);


INSERT INTO projects (projectname, profitmargin, totalcost, status, client_id) VALUES
                                                                                   ('Kitchen Remodel', 0.15, 5000, 'inprogress', 1),
                                                                                   ('Bathroom Renovation', 0.20, 3000, 'completed', 2),
                                                                                   ('Office Buildout', 0.10, 10000, 'cancelled', 3),
                                                                                   ('Living Room Expansion', 0.18, 8000, 'inprogress', 4),
                                                                                   ('Garage Conversion', 0.12, 7000, 'completed', 5);


INSERT INTO components (name, componenttype, vatrate) VALUES
                                                          ('Tiles', 'Material', 20.0),
                                                          ('Paint', 'Material', 20.0),
                                                          ('Laborer', 'Labor', 20.0),
                                                          ('Electrician', 'Labor', 20.0),
                                                          ('Plumber', 'Labor', 20.0);


INSERT INTO Materials (component_id, unitcost, quantity, transportcost, qualitycoefficient) VALUES
                                                                                                (1, 30, 20, 50, 1.1),
                                                                                                (2, 15, 10, 20, 1.0),
                                                                                                (1, 25, 15, 40, 1.2),
                                                                                                (2, 20, 8, 15, 1.3),
                                                                                                (1, 35, 25, 60, 1.0);

INSERT INTO Labor (component_id, hourlyrate, workhours, workerproductivity) VALUES
                                                                                (3, 20, 40, 1.0),
                                                                                (4, 35, 20, 1.1),
                                                                                (5, 30, 25, 1.0),
                                                                                (3, 25, 30, 1.2),
                                                                                (4, 40, 15, 1.3);


INSERT INTO Quotes (estimatedAmount, issuedate, isaccepted, project_id) VALUES
                                                                            (5000, '2024-09-10', true, 1),
                                                                            (3000, '2024-09-12', false, 2),
                                                                            (10000, '2024-09-15', true, 3),
                                                                            (8000, '2024-09-18', false, 4),
                                                                            (7000, '2024-09-20', true, 5);
