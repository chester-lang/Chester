package chester.tyck

trait MetaTycker[Self <: TyckerBase[Self] & TelescopeTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends Tycker[Self] {

}
