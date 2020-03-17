package com.mitocode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mitocode.model.Empleado;
import com.mitocode.model.TipoEmpleado;

@Mapper
public interface EmpleadoMapper {

	@Insert("INSERT INTO empleado(nombres,apellidos,documento,fechanacimiento,sueldo,numerohijos,usuario,clave,tipoempleado) "
			+ "VALUES (#{nombres}, #{apellidos}, #{documento}, #{fechaNacimiento}, #{sueldo}, #{numeroHijos}, #{usuario}, #{clave}, #{tipoEmpleado.idTipoEmpleado})")
	@Options(useGeneratedKeys = true, keyProperty = "idEmpleado", keyColumn = "idempleado")
	Integer registrar(Empleado empleado);

	@Update("UPDATE empleado SET nombres = #{nombres}, apellidos = #{apellidos}, documento = #{documento}, fechanacimiento = #{fechaNacimiento}, "
			+ "sueldo = #{sueldo}, numerohijos = #{numeroHijos}, usuario = #{usuario}, clave = #{clave}, tipoEmpleado = #{tipoEmpleado.idTipoEmpleado} WHERE idempleado = #{idEmpleado}")
	Integer actualizar(Empleado empleado);

	@Delete("DELETE FROM empleado WHERE idempleado = #{idEmpleado}")
	Integer eliminar(@Param("idEmpleado") Integer idEmpleado);

	@Select("SELECT * FROM empleado WHERE idempleado = #{idEmpleado}")
	@Results(@Result(property = "tipoEmpleado", javaType = TipoEmpleado.class, column = "tipoempleado", one = @One(select = "com.mitocode.mapper.TipoEmpleadoMapper.obtenerTipo")))
	Empleado obtenerPorId(@Param("idEmpleado") Integer idEmpleado);

	@Select("SELECT * FROM empleado")
	@Results(@Result(property = "tipoEmpleado", javaType = TipoEmpleado.class, column = "tipoempleado", one = @One(select = "com.mitocode.mapper.TipoEmpleadoMapper.obtenerTipo")))
	List<Empleado> obtenerEmpleados();

	@Select("SELECT * FROM empleado WHERE usuario = #{usuario} AND clave = #{clave}")
	@Results(@Result(property = "tipoEmpleado", javaType = TipoEmpleado.class, column = "tipoempleado", one = @One(select = "com.mitocode.mapper.TipoEmpleadoMapper.obtenerTipo")))
	Empleado login(@Param("usuario") String usuario, @Param("clave") String clave);

}
