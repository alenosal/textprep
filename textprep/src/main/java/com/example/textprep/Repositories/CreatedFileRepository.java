package com.example.textprep.Repositories;

import com.example.textprep.entity.CreatedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatedFileRepository extends JpaRepository<CreatedFile, String> {
}
