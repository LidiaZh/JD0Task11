package courses.dao;

import courses.entity.Mark;

import javax.persistence.EntityManager;

public class EntityDaoImplMark extends EntityDaoImpl<Mark> {

    private static EntityManager em;

    public EntityDaoImplMark() {
        super(Mark.class);
    }

}
