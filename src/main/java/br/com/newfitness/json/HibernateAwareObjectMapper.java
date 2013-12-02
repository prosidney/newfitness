package br.com.newfitness.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

    /**
	 * 
	 */
	private static final long serialVersionUID = -471372467428809407L;

	public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm);
    }
}
