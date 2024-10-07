/*με αυτην την procedure εμφανιζουμε ολες τις θεσεις εργασιας της εταιρειας του υπαλληλου που μπορει να ζητησει να αξιολογηθει*/
DELIMITER $
DROP PROCEDURE IF EXISTS select_jobs$
CREATE PROCEDURE select_jobs(IN onoma_xrhsth VARCHAR(12))
BEGIN
SELECT job.id,job.position,job.edra,job.salary,job.evaluator,job.announce_date,job.submission_date FROM job
INNER JOIN evaluator ON job.evaluator=evaluator.ev_username
INNER JOIN employee ON evaluator.com_afm=employee.c_afm
WHERE employee.e_username=onoma_xrhsth;
END $
DELIMITER ;

/*με αυτην την procedure εμφανιζουμε τις πληροφοριες ενος υπαλληλου*/
DELIMITER $
DROP PROCEDURE IF EXISTS employee_informations$
CREATE PROCEDURE employee_informations(IN onoma_xrhsth VARCHAR(12))
BEGIN
SELECT employee.bio AS Biografiko,employee.certificates AS Certificates,employee.sistatikes AS Sistatikes,employee.awards AS Awards,employee.c_afm AS AFM_Etaireias,
employee.employee_am AS AM,employee.years AS Xronia_Empeirias,languages.lang AS Glwsses,has_degree.degr_title AS Titlos,has_degree.degr_idryma AS Idryma,
degree.bathmida AS Bathmida,has_degree.etos AS Xronologia,has_degree.grade AS Bathmos FROM employee
INNER JOIN languages ON employee.e_username=languages.employe
INNER JOIN has_degree ON employee.e_username=has_degree.emp_username
INNER JOIN degree ON has_degree.degr_title=degree.titlos AND has_degree.degr_idryma=degree.idryma
WHERE employee.e_username=onoma_xrhsth;
END$
DELIMITER ;