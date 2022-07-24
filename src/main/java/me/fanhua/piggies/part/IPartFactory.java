package me.fanhua.piggies.part;

public interface IPartFactory<P extends IPart> {
	P create();
}
