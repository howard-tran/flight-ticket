package service

import (
	"GoBackend/entity"
	mongodbservice "GoBackend/service/repository-service"
	"fmt"
	"os"

	"gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"
)

const ProfileCollection = "Profile"

type ProfileService interface {
	AddProfile(enti entity.ProfileEntity) (bson.ObjectId, error)
	GetProfile(ID string) (entity.ProfileEntity, error)
	EditProfile(ID string, profile entity.ProfileEntity) error
	DelProfile() (bson.ObjectId, error)
}

type ProfileDataService struct {
	collection *mgo.Collection
}

func NewProfileService() (ProfileService, error) {
	sec, err := mongodbservice.NewDBService()
	if err != nil {
		msg := fmt.Sprintf("[ERROR] Database connect fail: %s", err.Error())
		fmt.Println(msg)
		return nil, err
	}
	return &ProfileDataService{
		collection: sec.GetDatabase(os.Getenv("NAME_DATABASE")).C(ProfileCollection),
	}, nil
}

func (c *ProfileDataService) GetProfile(ID string) (entity.ProfileEntity, error) {
	//bsonID :=
	objectId := bson.ObjectIdHex(ID)

	//fmt.Println(objectId)

	var result entity.ProfileEntity
	err := c.collection.Find(bson.M{"accountID": objectId}).One(&result)

	// //fmt.Println(result)
	// returnResult := result.Profile
	// returnResult.Username = result.Username
	if err != nil {
		return result, err
	}
	return result, nil
}

func (c *ProfileDataService) AddProfile(enti entity.ProfileEntity) (bson.ObjectId, error) {
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

func (c *ProfileDataService) EditProfile(ID string, profile entity.ProfileEntity) error {
	objectId := bson.ObjectIdHex(ID)
	fmt.Println(bson.M{"profile": objectId})

	err := c.collection.Update(bson.M{"accountID": objectId}, bson.M{"$set": profile})
	if err != nil {
		return err
	}
	return nil
}

func (c *ProfileDataService) DelProfile() (bson.ObjectId, error) {
	return "", nil
}
