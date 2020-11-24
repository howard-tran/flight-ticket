package controller

import "GoBackend/service"

var categoryservice service.CategoryService
var Sliderservice service.SliderService

func InitController() error {
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
