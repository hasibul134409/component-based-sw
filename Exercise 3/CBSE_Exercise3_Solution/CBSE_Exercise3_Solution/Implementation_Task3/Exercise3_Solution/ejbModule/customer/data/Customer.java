package customer.data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


/** 
 * Simple Customer datatype implemented as an Entity Bean (@Entity).
 * The used Database needs to be configured in META-INF/persistenz.xml
 * Implements the Serializeable Interface so it can be sent remotely to other beans/clients. 
 * Its Id is marked as the foreign key with @Id and is automatically generated by the time of its persistence (@GeneratedValue).
 */
@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Automatic generated foreign key by the time of persistence.
	 */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	protected long id;
	
	
	protected String name;
	protected boolean approved;
	
	/**
	 * @OneToOne is an annotation table mapping in JPA.
	 * It is necessary since Address is a complex Class that needs to be its own Entity Bean.
     * It means that one Address is connected to one Customer.
     * Cascade defines that all changes should be propagated to the Address table, too.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	protected Address address;
	
	/**
	 * needed for JPA
	 */
	public Customer(){}
	
	/**
	 * normal constructor
	 */
	public Customer(String name, Address address) {
		this.name = name;
		this.address = address;
		this.approved = false;
	}
	
	/**
	 * copy constructor, not really needed since we using a persistent Database, but good to have.
	 */
	public Customer(Customer original) {
		this.id = original.getId();
		this.name = original.getName();
		this.address = new Address(original.getAddress());
		this.approved = original.isApproved();
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "[Customer]" + " ID: (" + this.id + "); "+ "NAME: " + this.name + "; " + this.address;
	}
	

}
