package com.hunglevi.anazyler.entity.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_skills", schema = "core")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JobSkill {

    @EmbeddedId
    private JobSkillId id;
}
