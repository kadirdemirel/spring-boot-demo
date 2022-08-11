package etiya.startupProject.controllers;

import etiya.startupProject.business.abstracts.UserService;
import etiya.startupProject.core.utilities.results.DataResult;
import etiya.startupProject.core.utilities.results.Result;
import etiya.startupProject.entities.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserService userService;


    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid UserDto userDto) {
        return this.userService.add(userDto);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UserDto userDto) {
        return this.userService.update(userDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody UserDto userDto) {
        return this.userService.delete(userDto);
    }

    @GetMapping("/getall")
    public DataResult<List<UserDto>> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<UserDto> getById(@RequestParam int id) {
        return this.userService.getById(id);
    }
}
