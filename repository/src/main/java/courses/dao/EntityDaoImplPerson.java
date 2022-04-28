package courses.dao;

import courses.entity.Person;

public class EntityDaoImplPerson extends EntityDaoImpl<Person> {
    public EntityDaoImplPerson() {
        super(Person.class);
    }
}
