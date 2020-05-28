module hatzolahApp {
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.controls;
	requires java.sql;
	opens hatzalahGui; //took away the warning
	requires java.desktop;
}