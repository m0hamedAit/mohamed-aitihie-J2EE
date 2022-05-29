package ma.m0hamedait.ebankbackend.security.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import ma.m0hamedait.ebankbackend.security.entities.AppRole;
import ma.m0hamedait.ebankbackend.security.entities.AppUser;
import ma.m0hamedait.ebankbackend.security.service.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountRController {
    private AccountService accountService;

    @GetMapping("/users")
    public List<AppUser> getUsers(){
        return accountService.usersList();
    }

    @PostMapping("/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.addUser(appUser);
    }

    @PostMapping("/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addRole(appRole);
    }

    @PostMapping("/addRoleToUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRolename());
    }

}

//ToDo: handle if role or user doesn't exist

@Data
class RoleUserForm{
    private String username;
    private String rolename;
}