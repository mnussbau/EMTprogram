module hatzolahApp {
	requires transitive javafx.graphics;
	requires javafx.base;
	requires javafx.controls;
	requires java.sql;
	exports hatzalahGui;
	requires java.desktop;
}