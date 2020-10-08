package com.onpassive.springbootexceptionhttps.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<DBFileModel,String> {

}
