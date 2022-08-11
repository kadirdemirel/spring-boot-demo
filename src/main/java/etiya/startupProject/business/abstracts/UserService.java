package etiya.startupProject.business.abstracts;
import etiya.startupProject.core.utilities.results.DataResult;
import etiya.startupProject.core.utilities.results.Result;
import etiya.startupProject.entities.dtos.UserDto;

import java.util.List;

public interface UserService {
     Result add(UserDto userDto);

     Result update(UserDto userDto);

     Result delete(UserDto userDto);
     DataResult<List<UserDto>> getAll();

     DataResult<UserDto> getById(int id);

}
