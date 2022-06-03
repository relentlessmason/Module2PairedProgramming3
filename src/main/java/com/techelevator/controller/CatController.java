package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.dao.JdbcCatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatCardNotFoundException;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @GetMapping(path = "/random")
    public CatCard getRandom(){
        return new CatCard(catFactService.getFact().getText(), catPicService.getPic().getFile());
    }

    @GetMapping()
    public List<CatCard> listCollection(){
        return catCardDao.list();
    }

    @GetMapping(path = "/{id}")
    public CatCard get(@PathVariable long id)throws CatCardNotFoundException{
        return catCardDao.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public boolean save(@Valid @RequestBody CatCard cardToSave)throws CatCardNotFoundException{
        return catCardDao.save(cardToSave);
    }

    @PutMapping(path = "/{id}")
    public boolean update(@Valid @RequestBody CatCard catCard, @PathVariable long id)throws CatCardNotFoundException{
        return catCardDao.update(id, catCard);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) throws CatCardNotFoundException {
        catCardDao.delete(id);
    }





}
