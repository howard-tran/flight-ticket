package controller

import (
	"GoBackend/service"
	mongodbservice "GoBackend/service/repository-service"
)

var Databaseservice mongodbservice.DBService
var categoryservice service.CategoryService
var Sliderservice service.SliderService

func InitController() error {
	//NewDatabaseService
	d, err := mongodbservice.NewDBService()
	if err != nil {
		return err
	}
	Databaseservice = d

	//NewCategoryService
	c, err := service.NewCategoryService()
	if err != nil {
		return err
	}
	categoryservice = c

	//NewSliderService
	s, err := service.NewSliderService()
	if err != nil {
		return err
	}
	Sliderservice = s


	return nil
}
