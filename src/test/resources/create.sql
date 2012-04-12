create table UNIVERSITYAUDIT.TEACHERGROUP (
        GROUPID integer not null,
        NAME varchar(40) not null,
        primary key (GROUPID)
    );

    create table UNIVERSITYAUDIT.TEACHERGROUP_TL_LINK (
        TGROUPID integer not null,
        TLID integer not null,
        primary key (TGROUPID, TLID)
    );

    create table UNIVERSITYAUDIT.TEACHERLOGIN (
        ID integer not null,
        HUMANID integer not null unique,
        LOGIN varchar(20) not null unique,
        PASSWORD varchar(50) not null,
        primary key (ID)
    );

    create table UNIVERSITYAUDIT.TLGROUP (
        GROUPID integer not null,
        NAME varchar(40) not null,
        primary key (GROUPID)
    );

    create table UNIVERSITYAUDIT.TLGROUPTOROLELINK (
        ROLEID integer not null,
        GROUPID integer not null
    );

    create table UNIVERSITYAUDIT.TLROLES (
        ROLEID integer not null,
        NAME varchar(20) not null,
        primary key (ROLEID)
    );

    alter table UNIVERSITYAUDIT.TEACHERGROUP_TL_LINK
        add constraint FKE87ACAFF1EBE031D
        foreign key (TLID)
        references UNIVERSITYAUDIT.TEACHERLOGIN;

    alter table UNIVERSITYAUDIT.TEACHERGROUP_TL_LINK
        add constraint FKE87ACAFF1DAF8C70
        foreign key (TGROUPID)
        references UNIVERSITYAUDIT.TLGROUP;

    alter table UNIVERSITYAUDIT.TEACHERGROUP_TL_LINK
        add constraint FKE87ACAFF20F03906
        foreign key (TGROUPID)
        references UNIVERSITYAUDIT.TEACHERGROUP;

    alter table UNIVERSITYAUDIT.TLGROUPTOROLELINK
        add constraint FK6BD6DF12AFCB1159
        foreign key (ROLEID)
        references UNIVERSITYAUDIT.TLROLES;

    alter table UNIVERSITYAUDIT.TLGROUPTOROLELINK
        add constraint FK6BD6DF12BA73764
        foreign key (GROUPID)
        references UNIVERSITYAUDIT.TLGROUP;

    create sequence TEACHERGROUP_SEQ;

    create sequence TEACHERLOGIN_SEQ;

    create sequence TLGROUP_SEQ;

    create sequence TLROLES_SEQ;