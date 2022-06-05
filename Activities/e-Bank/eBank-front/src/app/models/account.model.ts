export interface AccountDetails {
  id:     string;
  balance:       number;
  currentPage:   number;
  totalPages:    number;
  pageSize:      number;
  operationDTOS : AccountOperations[];
}

export interface AccountOperations {
  id:            number;
  operationDate: Date;
  description:   string;
  amount:        number;
  type:          string;
  accountDTO:    null;
}
