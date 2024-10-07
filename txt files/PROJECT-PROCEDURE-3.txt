DELIMITER $
DROP PROCEDURE IF EXISTS complete_evaluations$
CREATE PROCEDURE complete_evaluations(IN thesh INT(4))
BEGIN
DECLARE numofrequestevaluations INT(4);
DECLARE numofcompleteevaluations INT(4);
DECLARE numofnullgrade INT(4);
DECLARE bathmos INT(4);
DECLARE not_found INT;
/*με αυτον τον cursor παιρνουμε τον συνολικο βαθμο απο καθε αξιολογηση για μια συγκεκριμενη θεση εργασιας */
DECLARE gcursor CURSOR FOR 
SELECT evaluationresult.total_grade FROM evaluationresult
WHERE evaluationresult.id_job=thesh;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET not_found=1;
/*εδω παιρνουμε το συνλικο αριθμο των αιτησεων που εχουν γινει για μια θεση εργασιας*/
SELECT COUNT(*) INTO numofrequestevaluations FROM requestevaluation WHERE requestevaluation.jobid=thesh;
/*εδω παιρνουμε το συνολικο αριθμο των αιτησεων που εχουν ολοκληρωθει για μια θεση εργασιας δηλαδη ο τελικος βαθμος στον πινακα evaluationresult δεν ειναι null*/
SELECT COUNT(*) INTO numofcompleteevaluations FROM evaluationresult WHERE evaluationresult.id_job=thesh AND evaluationresult.total_grade IS NOT NULL;
IF (numofrequestevaluations=0) THEN
    SELECT 'No request evaluations for this id';
ELSE
    /*αν το αθροισμα των συνολικων αιτησεων ειναι ισο με το αθροισμα των ολοκληρωμενων αιτησεων τοτε εμφανιζεται ποσες ειναι οι ολοκληρωμενες αιτησεις οπως επισης και ολοι οι υποψηφιοι με τις τελικες τους αξιολογησεις κατα φθινουσα σειρα */
    IF (numofcompleteevaluations=numofrequestevaluations) THEN
        SELECT numofcompleteevaluations AS 'Oristikopoihmenoi Pinakes';
        SELECT * FROM evaluationresult WHERE id_job=thesh ORDER BY total_grade DESC;
    ELSE
        /*αν το αθροισμα των συνολικων αιτησεων δεν ειναι ισο με το αθροισμα των ολοκληρωμενων αιτησεων με αυτον τον cursor ελεγχουμε ποσες αξιολογησεις εκρεμουν και εμφανιζουμε τις ολοκληρωμενες αιτησεις με ολους τους υποψηφιους με τις τελικες τους αξιολογησεις κατα φθινουσα σειρα */
        OPEN gcursor;
        SET not_found=0;
        FETCH gcursor INTO bathmos;
        SET numofnullgrade=0;
        WHILE (not_found=0) DO
    	    IF (bathmos IS NOT NULL) THEN
    	       SELECT * FROM evaluationresult WHERE id_job=thesh AND total_grade=bathmos ORDER BY total_grade DESC;
    	    ELSE
    		   SET numofnullgrade=numofnullgrade + 1;
    	    END IF;
        FETCH gcursor INTO bathmos;
        END WHILE;
        CLOSE gcursor;
        SELECT 'Axiologhsh se Exelixh',numofnullgrade as Ekremoun;
    END IF;
END IF;
END $
DELIMITER ;