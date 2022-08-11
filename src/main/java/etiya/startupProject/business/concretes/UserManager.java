package etiya.startupProject.business.concretes;

import etiya.startupProject.business.abstracts.UserService;
import etiya.startupProject.core.utilities.exceptions.BusinessException;
import etiya.startupProject.core.utilities.mapping.ModelMapperService;
import etiya.startupProject.core.utilities.results.DataResult;
import etiya.startupProject.core.utilities.results.Result;
import etiya.startupProject.core.utilities.results.SuccessDataResult;
import etiya.startupProject.core.utilities.results.SuccessResult;
import etiya.startupProject.dataAccess.abstracts.UserRepository;
import etiya.startupProject.entities.concretes.User;
import etiya.startupProject.entities.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    UserManager(UserRepository userRepository, ModelMapperService modelMapperService) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(UserDto userDto) {
        User user = requestMapping(userDto);
        this.userRepository.save(user);
        return new SuccessResult("USER.ADDED");
    }

    @Override
    public Result update(UserDto userDto) {
        checkIfUserExistsById(userDto.getId());
        User user = requestMapping(userDto);
        this.userRepository.save(user);
        return new SuccessResult("USER.UPDATED");

    }

    @Override
    public Result delete(UserDto userDto) {
        checkIfUserExistsById(userDto.getId());
        User user = requestMapping(userDto);
        this.userRepository.delete(user);
        return new SuccessResult("USER.DELETED");
    }

    @Override
    public DataResult<List<UserDto>> getAll() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> responses = users.stream().map(user -> this.modelMapperService.forResponse()
                .map(user, UserDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<UserDto>>(responses);

    }

    @Override
    public DataResult<UserDto> getById(int id) {
        User user = checkIfUserExistsById(id);
        UserDto response = this.modelMapperService.forResponse().map(user, UserDto.class);
        return new SuccessDataResult<UserDto>(response);
    }


    private User checkIfUserExistsById(int id) {
        User currentUser;
        try {
            currentUser = this.userRepository.findById(id).get();
        } catch (Exception e) {
            throw new BusinessException("USER.NOT.EXISTS");
        }
        return currentUser;

    }

    private User requestMapping(UserDto userDto) {
        User user = this.modelMapperService.forRequest().map(userDto, User.class);
        return user;
    }
}
