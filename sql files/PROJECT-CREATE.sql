DROP DATABASE project;
CREATE DATABASE project;
USE project;


CREATE TABLE user(
    username VARCHAR(12) NOT NULL,
    password VARCHAR(10) NOT NULL,
    name VARCHAR(25) NOT NULL,
    surname VARCHAR(35) NOT NULL,
    reg_date DATETIME DEFAULT NULL,
    email VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY(username)
);

CREATE TABLE companies_group(
    name VARCHAR(15) NOT NULL,
    PRIMARY KEY(name)
);

CREATE TABLE company(
	afm CHAR(9) NOT NULL,
	doy VARCHAR(15) NOT NULL,
	name VARCHAR(15) NOT NULL,
	phone BIGINT(16) NOT NULL,
	street VARCHAR(15) NOT NULL,
    numbr TINYINT(4) NOT NULL,
	city VARCHAR(15) NOT NULL,
	country VARCHAR(15) NOT NULL,
	group_name VARCHAR(15) NOT NULL,
	PRIMARY KEY(afm,group_name),
	CONSTRAINT GROUP_NAM FOREIGN KEY(group_name) REFERENCES companies_group(name) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE manager(
    man_username VARCHAR(12) NOT NULL,
    expyears TINYINT(4) NOT NULL DEFAULT 0,
    company_afm CHAR(9) NOT NULL,
    PRIMARY KEY(man_username),
    CONSTRAINT MAN_USER FOREIGN KEY(man_username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT C_AFM FOREIGN KEY(company_afm) REFERENCES company(afm) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE employee(
	e_username VARCHAR(12) NOT NULL,
	bio TEXT NOT NULL,
	certificates VARCHAR(50) NOT NULL,
    sistatikes VARCHAR(50) NOT NULL DEFAULT '',
	awards VARCHAR(50) NOT NULL DEFAULT '',
	c_afm CHAR(9) NULL,
	employee_am INT NOT NULL DEFAULT 0,
	years TINYINT(4) NOT NULL DEFAULT 0,
	PRIMARY KEY(e_username),
	CONSTRAINT EMP_USER FOREIGN KEY(e_username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT CO_AFM FOREIGN KEY(c_afm) REFERENCES company(afm) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE languages(
    employe VARCHAR(12) NOT NULL,
    lang SET('EN','GER','GR') NOT NULL DEFAULT '',
    PRIMARY KEY(employe,lang),
    CONSTRAINT EM_USER FOREIGN KEY(employe) REFERENCES employee(e_username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE degree(
  titlos varchar(100) NOT NULL,
  idryma varchar(40) NOT NULL,
  bathmida ENUM ('LYKEIO','UNIV','MASTER','PHD') DEFAULT NULL,
  PRIMARY KEY (titlos,idryma)
);

CREATE TABLE has_degree(
  degr_title varchar(100) NOT NULL,
  degr_idryma varchar(40) NOT NULL,
  emp_username varchar(12) NOT NULL,
  etos year(4) DEFAULT NULL,
  grade float(3,1) DEFAULT NULL,
  PRIMARY KEY(emp_username,degr_title,degr_idryma),
  CONSTRAINT EMPL_USER FOREIGN KEY(emp_username) REFERENCES employee(e_username) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT HAS_DGR FOREIGN KEY(degr_title,degr_idryma) REFERENCES degree(titlos,idryma) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE project(
    empl_username VARCHAR(12) NOT NULL,
    num TINYINT(4) NOT NULL DEFAULT 0,
    descr TEXT NULL,
    url VARCHAR(60) NULL,
    PRIMARY KEY(empl_username,num),
    CONSTRAINT EMPUSER FOREIGN KEY(empl_username) REFERENCES employee(e_username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE evaluator(
    ev_username VARCHAR(12) NOT NULL,
    com_afm CHAR(9) NULL,
    ev_id INT NOT NULL DEFAULT 0,
    xp_years TINYINT(4) NOT NULL DEFAULT 0,
    PRIMARY KEY(ev_username),
    CONSTRAINT EV_USER FOREIGN KEY(ev_username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT COM_AFM FOREIGN KEY(com_afm) REFERENCES company(afm) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE job(
    id INT(4) AUTO_INCREMENT,
    position VARCHAR(40) NOT NULL,
    edra VARCHAR(45) NOT NULL,
    salary FLOAT(6.1) NOT NULL,
    evaluator VARCHAR(12) NOT NULL,
    announce_date DATETIME DEFAULT NULL,
    submission_date DATE,
    PRIMARY KEY(id),
    CONSTRAINT EVA_USER FOREIGN KEY(evaluator) REFERENCES evaluator(ev_username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE antikeim(
    title VARCHAR(36) NOT NULL,
    descrb TINYTEXT NOT NULL,
    belongs_to VARCHAR(36),
    PRIMARY KEY(title),
    CONSTRAINT ANTIKEIMENA FOREIGN KEY(belongs_to) REFERENCES antikeim(title) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE needs(
    job_id INT(4) NOT NULL,
    title_antikeim VARCHAR(36) NOT NULL,
    PRIMARY KEY(job_id,title_antikeim),
    CONSTRAINT JOB_ID FOREIGN KEY(job_id) REFERENCES job(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT TTL_A FOREIGN KEY(title_antikeim) REFERENCES antikeim(title) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE requestevaluation(
    employee_user VARCHAR(12) NOT NULL,
    jobid INT(4) NOT NULL,
    PRIMARY KEY(employee_user,jobid),
    CONSTRAINT EMPLOYEE_USER FOREIGN KEY(employee_user) REFERENCES employee(e_username) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT JOBID FOREIGN KEY(jobid) REFERENCES job(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE evaluation_1(
    ypallhlos VARCHAR(12) NOT NULL,
    axiologhths VARCHAR(12) NOT NULL,
    kwdikos INT(4) NOT NULL,
    interview TEXT NULL,
    grade_1 ENUM('0','1','2','3','4') NULL,
    PRIMARY KEY(ypallhlos,axiologhths,kwdikos),
    CONSTRAINT YPAL1 FOREIGN KEY(ypallhlos) REFERENCES requestevaluation(employee_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT AXIO1 FOREIGN KEY(axiologhths) REFERENCES job(evaluator) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT KWD1 FOREIGN KEY(kwdikos) REFERENCES job(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE evaluation_2(
    ypallhlos_ev2 VARCHAR(12) NOT NULL,
    axiologhths_ev2 VARCHAR(12) NOT NULL,
    kwdikos_ev2 INT(4) NOT NULL,
    manager_report TEXT NULL,
    grade_2 ENUM('0','1','2','3','4') NULL,
    PRIMARY KEY(ypallhlos_ev2,axiologhths_ev2,kwdikos_ev2),
    CONSTRAINT YPAL2 FOREIGN KEY(ypallhlos_ev2) REFERENCES requestevaluation(employee_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT AXIO2 FOREIGN KEY(axiologhths_ev2) REFERENCES job(evaluator) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT KWD2 FOREIGN KEY(kwdikos_ev2) REFERENCES job(id) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE evaluation_3(
    ypallhlos_ev3 VARCHAR(12) NOT NULL,
    axiologhths_ev3 VARCHAR(12) NOT NULL,
    kwdikos_ev3 INT(4) NOT NULL,
    grade_3 ENUM('0','1','2') NULL,
    PRIMARY KEY(ypallhlos_ev3,axiologhths_ev3,kwdikos_ev3),
    CONSTRAINT YPAL3 FOREIGN KEY(ypallhlos_ev3) REFERENCES requestevaluation(employee_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT AXIO3 FOREIGN KEY(axiologhths_ev3) REFERENCES job(evaluator) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT KWD3 FOREIGN KEY(kwdikos_ev3) REFERENCES job(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE evaluationresult(
    evid INT(4) NULL AUTO_INCREMENT,
    em_username VARCHAR(12) NOT NULL,
    id_job INT(4) NOT NULL,
    total_grade INT(4) DEFAULT NULL,
    comments TEXT(255) DEFAULT NULL,
    PRIMARY KEY(evid,em_username),
    CONSTRAINT EMPL FOREIGN KEY(em_username) REFERENCES requestevaluation(employee_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT JID FOREIGN KEY(id_job) REFERENCES job(id) ON DELETE CASCADE ON UPDATE CASCADE
);
