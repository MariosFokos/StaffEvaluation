--Αυτος ο trigger συμπληρωνει αυτοματα το id των υπαλληλων μιας εταιρειας παιρνοντας,καθε φορα που κανουμε insert εναν νεο υπαλληλο στην εταιραια,το αθροισμα των ηδη υπαρχοντων υπαλληλων και προσθετει +1 και το αποτελεσμα το αποθηκευει ως το νεο id του καινουριου υπαλληλου
DELIMITER $
DROP TRIGGER IF EXISTS employee_am$
CREATE TRIGGER employee_am
BEFORE INSERT ON employee
FOR EACH ROW 
BEGIN
DECLARE numofemployees INT;
SELECT COUNT(*) INTO numofemployees
FROM employee WHERE employee.c_afm=new.c_afm;
SET new.employee_am= numofemployees + 1;
END $ 
DELIMITER ;

--Ομοιως με τον employee_am αυτος ο trigger συμπληρωνει αυτοματα τον αριθμο των project ενος υπαλληλου
DELIMITER $
DROP TRIGGER IF EXISTS project_num$
CREATE TRIGGER project_num
BEFORE INSERT ON project
FOR EACH ROW
BEGIN
DECLARE numofprojects INT;
SELECT COUNT(*) INTO numofprojects
FROM project WHERE project.empl_username=new.empl_username;
SET new.num= numofprojects + 1;
END $ 
DELIMITER ;

--Ομοιως με τον employee_am αυτος ο trigger συμπληρωνει αυτοματα το id ενος αξιολογητη σε μια εταιρεια
DELIMITER $
DROP TRIGGER IF EXISTS eva_id$
CREATE TRIGGER eva_id
BEFORE INSERT ON evaluator
FOR EACH ROW 
BEGIN
DECLARE numofevaluators INT;
SELECT COUNT(*) INTO numofevaluators
FROM evaluator WHERE evaluator.com_afm=new.com_afm;
SET new.ev_id= numofevaluators + 1;
END $ 
DELIMITER ;

--Αυτος ο trigger τοποθετει αρχικες τιμες στον πινακα evaluationresult μετα την εισαγωγη μιας αιτησης για αξιολογηση απο εναν υπαλληλο
DELIMITER $
DROP TRIGGER IF EXISTS crt_evaluationresult$
CREATE TRIGGER crt_evaluationresult
AFTER INSERT ON requestevaluation
FOR EACH ROW
BEGIN
INSERT INTO evaluationresult(em_username,id_job)
VALUES (new.employee_user,new.jobid);
END$
DELIMITER ;

--Ομοιως με τον crt_evaluationresult αυτος ο trigger τοποθετει αρχικες τιμες στον evaluation_1
DELIMITER $
DROP TRIGGER IF EXISTS crt_evaluationresult1$
CREATE TRIGGER crt_evaluationresult1
AFTER INSERT ON requestevaluation
FOR EACH ROW
BEGIN
INSERT INTO evaluation_1(ypallhlos,axiologhths,kwdikos)
SELECT requestevaluation.employee_user,job.evaluator,job.id FROM requestevaluation
INNER JOIN job ON requestevaluation.jobid=job.id
WHERE new.jobid=job.id AND new.employee_user=requestevaluation.employee_user;
END$
DELIMITER ;

--Ομοιως με τον crt_evaluationresult αυτος ο trigger τοποθετει αρχικες τιμες στον evaluation_2
DELIMITER $
DROP TRIGGER IF EXISTS crt_evaluationresult2$
CREATE TRIGGER crt_evaluationresult2
AFTER INSERT ON requestevaluation
FOR EACH ROW
BEGIN
INSERT INTO evaluation_2(ypallhlos_ev2,axiologhths_ev2,kwdikos_ev2)
SELECT requestevaluation.employee_user,job.evaluator,job.id FROM requestevaluation
INNER JOIN job ON requestevaluation.jobid=job.id
WHERE new.jobid=job.id AND new.employee_user=requestevaluation.employee_user;
END$
DELIMITER ;

--Ομοιως με τον crt_evaluationresult αυτος ο trigger τοποθετει αρχικες τιμες στον evaluation_3
DELIMITER $
DROP TRIGGER IF EXISTS crt_evaluationresult3$
CREATE TRIGGER crt_evaluationresult3
AFTER INSERT ON requestevaluation
FOR EACH ROW
BEGIN
INSERT INTO evaluation_3(ypallhlos_ev3,axiologhths_ev3,kwdikos_ev3)
SELECT requestevaluation.employee_user,job.evaluator,job.id FROM requestevaluation
INNER JOIN job ON requestevaluation.jobid=job.id
WHERE new.jobid=job.id AND new.employee_user=requestevaluation.employee_user;
END$
DELIMITER ;

--Αυτος ο trigger συνδεει την συνεντευξη στην πρωτη αξιολογηση με τα σχολια στο αποτελεσμα της αξιολογησης,κατα την εισαγωγη μιας συνεντευξης γινεται ενημερωση στα σχολια στον πινακα evaluationresult
DELIMITER $
DROP TRIGGER IF EXISTS result_1$
CREATE TRIGGER result_1
AFTER INSERT ON evaluation_1
FOR EACH ROW 
BEGIN
UPDATE evaluationresult 
SET evaluationresult.comments=new.interview
WHERE evaluationresult.em_username=new.ypallhlos AND evaluationresult.id_job=new.kwdikos;
END$
DELIMITER ;

--Αυτος ο trigger ειναι παρομοιος με τον result_1,η μονη διαφορα ειναι οτι κατα την ενημερωση της στηλης interview στον evaluation_1 γινεται ενημερωση της στηλης comments στον evaluationresult
DELIMITER $
DROP TRIGGER IF EXISTS emp_interview$
CREATE TRIGGER emp_interview
AFTER UPDATE ON evaluation_1
FOR EACH ROW
BEGIN
UPDATE evaluationresult
SET evaluationresult.comments=new.interview
WHERE evaluationresult.em_username=old.ypallhlos AND evaluationresult.id_job=old.kwdikos;
END$
DELIMITER ;

--Αυτος ο trigger κατα την ενημερωση(δηλαδη εισαγωγη βαθμου και συνεντευξης αρα και ολοκληρωσης του) καλει την store procedure του ερωτηματος 3.2 και ελεγχει αν και οι τρεις επιμερους αξιολογησεις ειναι ολοκληρωμενες(δηλαδη εχουν βαθμο) και εφοσον αυτο ισχυει συμπληρωνει τον πινακα evaluationresult
DELIMITER $
DROP TRIGGER IF EXISTS check_evaluation1$
CREATE TRIGGER check_evaluation1
AFTER UPDATE ON evaluation_1
FOR EACH ROW
BEGIN
CALL check_evaluations(new.axiologhths,new.kwdikos);
END$
DELIMITER ;

--Ομοιως με τον check_evaluation1
DELIMITER $
DROP TRIGGER IF EXISTS check_evaluation2$
CREATE TRIGGER check_evaluation2
AFTER UPDATE ON evaluation_2
FOR EACH ROW
BEGIN
CALL check_evaluations(new.axiologhths_ev2,new.kwdikos_ev2);
END$
DELIMITER ;

--Ομοιως με τον check_evaluation1
DELIMITER $
DROP TRIGGER IF EXISTS check_evaluation3$
CREATE TRIGGER check_evaluation3
AFTER UPDATE ON evaluation_3
FOR EACH ROW
BEGIN
CALL check_evaluations(new.axiologhths_ev3,new.kwdikos_ev3);
END$
DELIMITER ;

--Εαν ενας υπαλληλος διαγραψει την αιτηση του για αξιολογηση αυτος o trigger διαγραφει απο τον πινακα evaluation_1 την γραμμη που εχει σχεση με την διεγραμμενη αιτηση για αξιολογηση
DELIMITER $
DROP TRIGGER IF EXISTS dlt_evaluationresult1$
CREATE TRIGGER dlt_evaluationresult1
AFTER DELETE ON requestevaluation
FOR EACH ROW
BEGIN
DELETE FROM evaluation_1 
WHERE old.jobid=evaluation_1.kwdikos AND old.employee_user=evaluation_1.ypallhlos;
END$
DELIMITER ;

--Ομοιως με τον dlt_evaluationresult1 για τον πινακα evaluation_2
DELIMITER $
DROP TRIGGER IF EXISTS dlt_evaluationresult2$
CREATE TRIGGER dlt_evaluationresult2
AFTER DELETE ON requestevaluation
FOR EACH ROW
BEGIN
DELETE FROM evaluation_2
WHERE old.jobid=evaluation_2.kwdikos_ev2 AND old.employee_user=evaluation_2.ypallhlos_ev2;
END$
DELIMITER ;

--Ομοιως με τον dlt_evaluationresult1 για τον πινακα evaluation_3
DELIMITER $
DROP TRIGGER IF EXISTS dlt_evaluationresult3$
CREATE TRIGGER dlt_evaluationresult3
AFTER DELETE ON requestevaluation
FOR EACH ROW
BEGIN
DELETE FROM evaluation_3
WHERE old.jobid=evaluation_3.kwdikos_ev3 AND old.employee_user=evaluation_3.ypallhlos_ev3;
END$
DELIMITER ;

--Ομοιως με τον dlt_evaluationresult1 για τον πινακα evaluationresult
DELIMITER $
DROP TRIGGER IF EXISTS dlt_evaluationresult$
CREATE TRIGGER dlt_evaluationresult
AFTER DELETE ON requestevaluation
FOR EACH ROW
BEGIN
DELETE FROM evaluationresult
WHERE old.jobid=evaluationresult.id_job AND old.employee_user=evaluationresult.em_username;
END$
DELIMITER ;

--Αυτος ο trigger κατα την εισαγωγη νεου χρηστη στον πινακα user θετει στην ημερομηνια εγγραφης την τρεχουσα ημερομηνια και ωρα
DELIMITER $
DROP TRIGGER IF EXISTS set_reg_date$
CREATE TRIGGER set_reg_date
BEFORE INSERT ON user 
FOR EACH ROW 
BEGIN
SET new.reg_date=CURRENT_TIMESTAMP;
END $
DELIMITER ;

--Αυτος ο trigger κατα την εισαγωγη μιας νεας θεσης εργασιας στον πινακα job θετει στην ημερομηνια ανακοινωσης την τρεχουσα ημερομηνια και ωρα και ελεγχει εαν η ημερομηνια ληξης των αιτησεων ειναι μεταγενεστερη απο την ημερομηνια ανακοινωσης
DELIMITER $ 
DROP TRIGGER IF EXISTS set_announce_date$
CREATE TRIGGER set_announce_date
BEFORE INSERT ON job
FOR EACH ROW
BEGIN
SET new.announce_date=CURRENT_TIMESTAMP;
IF DATEDIFF(new.submission_date,new.announce_date)<0 THEN
	SIGNAL SQLSTATE VALUE '45000' SET MESSAGE_TEXT = 'Wrong Submission Date';
END IF;
END$
DELIMITER ;

--Αυτος ο trigger ελεγχει αν τουλαχιστον μια απο τις επιμερους αξιολογησεις εχει ολοκληρωθει(δηλαδη εχει μπει βαθμος) επομενως μπλοκαρει την προσπαθεια διαγραφης στον πινακα requestevaluation
DELIMITER $
DROP TRIGGER IF EXISTS dlt_requestevaluation$
CREATE TRIGGER dlt_requestevaluation
BEFORE DELETE ON requestevaluation
FOR EACH ROW 
BEGIN
DECLARE bathmos_1 INT(4);
DECLARE bathmos_2 INT(4);
DECLARE bathmos_3 INT(4);
SELECT evaluation_1.grade_1 INTO bathmos_1 FROM evaluation_1 
WHERE old.employee_user=evaluation_1.ypallhlos AND old.jobid=evaluation_1.kwdikos;
SELECT evaluation_2.grade_2 INTO bathmos_2 FROM evaluation_2 
WHERE old.employee_user=evaluation_2.ypallhlos_ev2 AND old.jobid=evaluation_2.kwdikos_ev2;
SELECT evaluation_3.grade_3 INTO bathmos_3 FROM evaluation_3 
WHERE old.employee_user=evaluation_3.ypallhlos_ev3 AND old.jobid=evaluation_3.kwdikos_ev3; 
IF(bathmos_1 IS NOT NULL OR bathmos_2 IS NOT NULL OR bathmos_3 IS NOT NULL) THEN 
	SIGNAL SQLSTATE VALUE '45000' SET MESSAGE_TEXT = 'Evaluation is active or finished';
END IF;
END$
DELIMITER ;

--Αυτος ο trigger ελεγχει εαν η ημερομηνια ληξης των αιτησεων ειναι μεταγενεστερη της τωρινης ημερομηνιας,εαν αυτο ισχυει μπλοκαρει την εισαγωγη νεας αιτησης αξιολογησης για την συγκεκριμενη θεση εργασιας
DELIMITER $
DROP TRIGGER IF EXISTS insrt_requestevaluation$
CREATE TRIGGER insrt_requestevaluation
BEFORE INSERT ON requestevaluation
FOR EACH ROW 
BEGIN
DECLARE hmerominia DATE;
DECLARE telikh_hmerominia DATE;
SET hmerominia=CURRENT_TIMESTAMP;
SELECT job.submission_date INTO telikh_hmerominia FROM job
WHERE new.jobid=job.id;
IF DATEDIFF(telikh_hmerominia,hmerominia)<0 THEN 
	SIGNAL SQLSTATE VALUE '45000' SET MESSAGE_TEXT = 'Submission date expired';
END IF;
END$
DELIMITER ;

--Αυτος ο trigger εμποδιζει την αλλαγη του αφμ,του δου,του ονοματος και του ονοματος του ομιλου μιας εταιρειας
DELIMITER $
DROP TRIGGER IF EXISTS update_company$
CREATE TRIGGER update_company
BEFORE UPDATE ON company 
FOR EACH ROW
BEGIN
IF (old.afm!=new.afm OR old.doy!=new.doy OR old.name!=new.name OR old.group_name!=new.group_name ) THEN
	SET new.afm=old.afm;
	SET new.doy=old.doy;
	SET new.name=old.name;
	SET new.group_name=old.group_name;
END IF;
END$
DELIMITER ;