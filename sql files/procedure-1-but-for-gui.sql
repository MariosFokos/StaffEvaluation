/*Παρομοια με την procedure AithshYpallhlou η μονη διαφορα ειναι οτι κοιταμε τους υπαλληλους μια εταιρειας και αυτο το πετυχαινουμε με την εισαγωγη του αφμ της εταιρειας οταν καλουμε την procedure μας */
DELIMITER $
DROP PROCEDURE IF EXISTS AithshYpallhlou1$
CREATE PROCEDURE AithshYpallhlou1(IN onoma_ypallhlou VARCHAR(25),IN epi8eto_ypallhlou VARCHAR(35),IN afm_etaireias CHAR(9))
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
DECLARE ecursor CURSOR FOR
SELECT requestevaluation.jobid,evaluationresult.evid,evaluationresult.total_grade,evaluationresult.comments,user.name,user.surname FROM requestevaluation
INNER JOIN evaluationresult ON requestevaluation.jobid=evaluationresult.id_job 
INNER JOIN job ON evaluationresult.id_job=job.id
INNER JOIN user ON job.evaluator=user.username
WHERE onoma_xrhsth=requestevaluation.employee_user;
DECLARE ucursor CURSOR FOR
SELECT user.username FROM user
INNER JOIN employee ON employee.e_username=user.username
INNER JOIN company ON employee.c_afm=company.afm
INNER JOIN manager ON company.afm=manager.company_afm
WHERE user.name=onoma_ypallhlou AND user.surname=epi8eto_ypallhlou AND company.afm=afm_etaireias;
DECLARE bcursor CURSOR FOR
SELECT evaluation_1.grade_1,evaluation_2.grade_2, evaluation_3.grade_3 FROM evaluation_1
INNER JOIN evaluation_2 ON evaluation_1.ypallhlos=evaluation_2.ypallhlos_ev2 AND evaluation_1.kwdikos=evaluation_2.kwdikos_ev2
INNER JOIN evaluation_3 ON evaluation_2.ypallhlos_ev2=evaluation_3.ypallhlos_ev3 AND evaluation_2.kwdikos_ev2=evaluation_3.kwdikos_ev3
WHERE evaluation_1.ypallhlos=onoma_xrhsth AND evaluation_1.kwdikos=idjob; 
DECLARE CONTINUE HANDLER FOR NOT FOUND SET not_found=1;
OPEN ucursor;
SET not_found=0;
FETCH ucursor INTO onoma_xrhsth;
IF (not_found=1) THEN
    SELECT 'This user is not an employee';
END IF;
WHILE (not_found=0) DO
    OPEN ecursor;
    SET not_found=0;
    FETCH ecursor INTO idjob,idev,bathmos,sxolia,onoma_axiologhth,epi8eto_axiologhth;
    IF (not_found=1) THEN
        SELECT 'Employee has no requested an evaluation';
    END IF;
    WHILE (not_found=0) DO
        IF (bathmos IS NOT NULL) THEN
            SELECT idev,idjob,bathmos,sxolia,onoma_axiologhth,epi8eto_axiologhth;
        ELSE
            SELECT 'Incomplete Total Evaluation',idev as kwdikos_axiologhshs;
            OPEN bcursor;
            SET not_found=0;
            FETCH bcursor INTO bathmos_1,bathmos_2,bathmos_3;
            IF (not_found=0) THEN
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