package ec.com.ebos.hibernate.bitacora.process;

import org.springframework.stereotype.Repository;

import ec.com.ebos.core.bitacora.exception.BitacoraException;
import ec.com.ebos.core.bitacora.process.BitacoraP;
import ec.com.ebos.hibernate.root.process.RootPImpl;

/**
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * @since 2013-03-12
 * 
 */
@Repository("bitacoraG")
public class BitacoraPImpl extends RootPImpl<Object, BitacoraException> implements BitacoraP {

	private static final long serialVersionUID = -2677308041792049091L;
	
}
