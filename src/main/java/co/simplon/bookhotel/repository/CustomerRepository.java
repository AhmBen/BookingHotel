package co.simplon.bookhotel.repository;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.bookhotel.model.Customer;


@Named
public interface CustomerRepository  extends JpaRepository<Customer, Long>{
}
