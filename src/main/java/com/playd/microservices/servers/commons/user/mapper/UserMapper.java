package com.playd.microservices.servers.commons.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

	int insertUser(Object parameter) throws Exception;
}
