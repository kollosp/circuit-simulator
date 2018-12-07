package electricCircut;


/**\
 * Interface zawierajacy deklaracje funkcji obsugujacych stan 
 * plik: Statable.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public interface Statable {

	public void setState(int state);
	public int state();
	public void clear();
}
