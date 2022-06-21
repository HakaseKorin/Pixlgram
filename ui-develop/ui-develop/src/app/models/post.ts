import { User } from "./user";
import { Comment} from "./comment";
import { PageofItems } from "./pageofitems";


export interface Post {
    id: number;
    user: User;
    img: string;
    description: string;
    createdOn: Date;
    comments: PageofItems<Comment>;
}
