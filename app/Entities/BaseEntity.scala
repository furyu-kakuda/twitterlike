package Entities

import com.googlecode.mapperdao.{DeclaredIds, Entity}
import model.BaseModel

trait BaseEntity[PC <: DeclaredIds[Long], T <: BaseModel] extends Entity[Long, PC, T] {
  val id = key("id") to (_.id)
  val createdTime = column("created_time") to (_.createdTime)
  val updatedTime = column("updated_time") to (_.updatedTime)
}
