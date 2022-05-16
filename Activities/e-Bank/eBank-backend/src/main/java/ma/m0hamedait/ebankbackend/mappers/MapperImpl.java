package ma.m0hamedait.ebankbackend.mappers;

import ma.m0hamedait.ebankbackend.dtos.*;
import ma.m0hamedait.ebankbackend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service  // car on va l'injecter dans la couche
public class MapperImpl {

    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);  // customerDTO.setAttributes(customer.getAttributes)
        return customerDTO;
    };


    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    };

    public AccountDTO fromAccount(Account account){
        if(account instanceof SavingAccount)
            return fromSavingAccount((SavingAccount) account);
        else
            return fromCurrentAccount((CurrentAccount) account);
    }

    public SavingAccountDTO fromSavingAccount(SavingAccount savingAccount){
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountDTO);
        savingAccountDTO.setOwner(fromCustomer(savingAccount.getOwner()));
        savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDTO;
    }

    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        savingAccount.setOwner(fromCustomerDTO(savingAccountDTO.getOwner()));
        return savingAccount;
    }

    public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentAccountDTO);
        currentAccountDTO.setOwner(fromCustomer(currentAccount.getOwner()));
        currentAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentAccountDTO;
    }

    public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, currentAccount);
        currentAccount.setOwner(fromCustomerDTO(currentAccountDTO.getOwner()));
        return currentAccount;
    }

    public OperationDTO fromOperation(Operation operation){
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation, operationDTO);
        return operationDTO;
    }

    public Operation fromOperationDTO(OperationDTO operationDTO){
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDTO, operation);
        return operation;
    }

}
