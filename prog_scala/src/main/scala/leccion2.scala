import proglogica.LogicSFK._

object Leccion2 {
  def main(args: Array[String]) ={

  	def nat:T[Int]= unit(0) | nat.map(_ + 1)
  	val resultado:List[Int]=run(nat,10)

  	println(resultado)

  } 
}