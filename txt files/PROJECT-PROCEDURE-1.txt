DELIMITER $
DROP PROCEDURE IF EXISTS AithshYpallhlou$
CREATE PROCEDURE AithshYpallhlou(IN onoma_ypallhlou VARCHAR(25),IN epi8eto_ypallhlou VARCHAR(35))
BEGIN
DECLARE onoma_xrhsth VARCHAR(12);
DECLARE idjob INT(4);
DECLARE idev INT(4);
DECLARE bathmos INT(4);
DECLARE sxolia VARCHAR(255);  
DECLARE onoma_axiologhth VARCHAR(25);
DECLARE epi8eto_axiologhth VARCHAR(35);
DECLARE bathmos_1 INT(4);
DECLARE bathmos_2 INT(4);
DECLARE bathmos_3 INT(4);
DECLARE not_found INT;
/*χρησημοποιουμε cursor εδω γιατι ενας υπαλληλος μπορει να εχει κανει για παραπανω απο μια θεση εργασιας αιτηση για αξιολογηση*/
DECLARE ecursor CURSOR FOR
SELECT requestevaluation.jobid,evaluationresult.evid,evaluationresult.total_grade,evaluationresult.comments,user.name,user.surname FROM requestevaluation
INNER JOIN evaluationresult ON requestevaluation.jobid=evaluationresult.id_job 
INNER JOIN job ON evaluationresult.id_job=job.id
INNER JOIN user ON job.evaluator=user.username
WHERE onoma_xrhsth=requestevaluation.employee_user;
/*η χρηση του cursor εδω περα ειναι για να βρουμε το username του υπαλληλου απο το ονομα και το επιθετο που καλουμε με την procedure μας*/
DECLARE ucursor CURSOR FOR
SELECT user.username FROM user
INNER JOIN employee ON employee.e_username=user.username
WHERE user.name=onoma_ypallhlou AND user.surname=epi8eto_ypallhlou;
/*χρησημοποιουμε cursor εδω διοτι μπορει ενας υπαλληλος να εχει περισσοτερες απο μια αξιολογησεις*/
DECLARE bcursor CURSOR FOR
SELECT evaluation_1.grade_1,evaluation_2.grade_2, evaluation_3.grade_3 FROM evaluation_1
INNER JOIN evaluation_2 ON evaluation_1.ypallhlos=evaluation_2.ypallhlos_ev2 AND evaluation_1.kwdikos=evaluation_2.kwdikos_ev2
INNER JOIN evaluation_3 ON evaluation_2.ypallhlos_ev2=evaluation_3.ypallhlos_ev3 AND evaluation_2.kwdikos_ev2=evaluation_3.kwdikos_ev3
WHERE evaluation_1.ypallhlos=onoma_xrhsth AND evaluation_1.kwdikos=idjob; 
DECLARE CONTINUE HANDLER FOR NOT FOUND SET not_found=1;
OPEN ucursor;
SET not_found=0;
/*με την εντολη fetch βαζουμε την τιμη που εχει ο cursor στο onoma_xrhsth*/
FETCH ucursor INTO onoma_xrhsth;
/*αν δεν υπαρχει τιμη συμαινει πως το ονομα η το επιθετο που βαλαμε οταν καλεσαμε την procedure ειναι λανθασμενο η δεν αντιστοιχει σε υπαλληλο*/
IF (not_found=1) THEN
    SELECT 'This user is not an employee';
END IF;
/*ν υπαρχςει τιμη στον cursor τοτε μπαινουμε στην εντολη while*/
WHILE (not_found=0) DO
    OPEN ecursor;
    SET not_found=0;
    /*με την εντολη fetch βαζουμε τιμες στον ecursor*/
    FETCH ecursor INTO idjob,idev,bathmos,sxolia,onoma_axiologhth,epi8eto_axiologhth;
    /*αν δεν υπαρχουν τιμες συμαινει οτι συγκεκριμενος υπαλληλος δεν εχει κανει αιτηση για να αξιολογηθει*/
    IF (not_found=1) THEN
        SELECT 'Employee has no requested an evaluation';
    END IF;
    /*αν υπαρχουν τιμες τοτε μπαινουμε στην εντολη while*/
    WHILE (not_found=0) DO
        /*εαν η αξιολογηση εχει ολοκληρωθει ο τελικος βαθμος δεν πρεπει να ειναι null*/
        IF (bathmos IS NOT NULL) THEN
            SELECT idev,idjob,bathmos,sxolia,onoma_axiologhth,epi8eto_axiologhth;
        ELSE
            SELECT 'Incomplete Total Evaluation',idev as kwdikos_axiologhshs;
            OPEN bcursor;
            SET not_found=0;
            /*σε αυτο το fetch βαζουμε τις τιμες των βαθμων απο τις 3 επιμερους αξιολογησεις*/
            FETCH bcursor INTO bathmos_1,bathmos_2,bathmos_3;
            IF (not_found=0) THEN
                    /*εδω εξεταζουμε ποια απο τις 3 αξιολογησεις δεν ειναι ολοκληρωμενη*/
                    IF (bathmos_1 IS NULL) THEN
                        SELECT 'Incomplete Evaluation 1';
                    ELSE
                        SELECT * FROM evaluation_1 WHERE evaluation_1.kwdikos=idjob;
                    END IF;
                    IF (bathmos_2 IS NULL) THEN
                        SELECT 'Incomplete Evaluation 2';
                    ELSE
                        SELECT * FROM evaluation_2 WHERE evaluation_2.kwdikos_ev2=idjob;
                    END IF;
                    IF (bathmos_3 IS NULL) THEN
                        SELECT 'Incomplete Evaluation 3'; 
                    ELSE
                        SELECT * FROM evaluation_3 WHERE evaluation_3.kwdikos_ev3=idjob;
                    END IF;       
            END IF;
             CLOSE bcursor;
        END IF;
    FETCH ecursor INTO idjob,idev,bathmos,sxolia,onoma_axiologhth,epi8eto_axiologhth;
    END WHILE;
    CLOSE ecursor;
    FETCH ucursor INTO onoma_xrhsth;
END WHILE;
CLOSE ucursor;
END$
DELIMITER ;