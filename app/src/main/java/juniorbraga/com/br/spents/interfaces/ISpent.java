package juniorbraga.com.br.spents.interfaces;

import com.example.aplication.service.IResponse;

import java.util.List;

import juniorbraga.com.br.spents.model.Spent;

public interface ISpent {

    interface AddSpent extends IResponse {
        void showSucessAdd();
        void showError(String Error);
    }

    interface GetListSpent extends IResponse{
        void showList(List<Spent> spentList);
    }
}
