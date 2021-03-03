package service.customer;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
    private static List<Customer> customerList;

    static {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1, "Vinh", "Ha Noi"));
        customerList.add(new Customer(2, "Qui", "Vinh Phuc"));
    }

    @Override
    public List<Customer> findAll() {
        return customerList;
    }

    @Override
    public Customer findById(int id) {
        for (Customer c : customerList) {
            if (id == c.getId()) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void update(Customer customer, int id) {
        customerList.set(id, customer);
    }


    @Override
    public void save(Customer customer) {
        customerList.add(customer);
    }

    @Override
    public void removeCustomer(int id) {
        Customer c = findById(id);
        customerList.remove(c);
    }
}
