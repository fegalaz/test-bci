package test.bci.com.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import test.bci.com.dto.UserDTO;
import test.bci.com.repositories.entities.Users;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //@Mapping(source = "id", target = "id")
    UserDTO entityToUserDto(Users users);
}
