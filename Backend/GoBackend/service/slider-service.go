package service

import (
	"GoBackend/entity"
	mongodbservice "GoBackend/service/repository-service"
	"fmt"
	"os"

	"gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"
)

const SliderCollection = "Slider"

type SliderService interface {
	AddSlider(enti entity.SliderEntity) (bson.ObjectId, error)
	GetAllSlider() ([]entity.SliderEntity, error)
	EditSlider() (bool, error)
	DelSlider() (bson.ObjectId, error)
}

type SliderDataService struct {
	collection *mgo.Collection
}

func NewSliderService() (SliderService, error) {
	sec, err := mongodbservice.NewDBService()
	if err != nil {
		msg := fmt.Sprintf("[ERROR] Database connect faile: %s", err.Error())
		fmt.Println(msg)
		return nil, err
	}
	return &SliderDataService{
		collection: sec.GetDatabase(os.Getenv("NAME_DATABASE")).C(SliderCollection),
	}, nil
}

func (c *SliderDataService) GetAllSlider() ([]entity.SliderEntity, error) {
	var result []entity.SliderEntity
	err := c.collection.Find(bson.M{}).All(&result)

	if err != nil {
		return nil, err
	}
	return result, nil
}

func (c *SliderDataService) AddSlider(enti entity.SliderEntity) (bson.ObjectId, error) {
	_id := bson.NewObjectId()
	enti.ID = _id
	err := c.collection.Insert(&enti)

	//handle more...

	//

	if err != nil {
		return "", err
	}
	return _id, nil
}

func (c *SliderDataService) EditSlider() (bool, error) {
	return false, nil
}

func (c *SliderDataService) DelSlider() (bson.ObjectId, error) {
	return "", nil
}
