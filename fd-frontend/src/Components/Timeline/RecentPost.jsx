import styles from '../../css/RecentPost.module.css'
import ProfilePicture from '../Homepage/ProfilePicture'
import { useQuery } from 'react-query'
import { fetchData } from '../../utils/api'

import PropTypes from 'prop-types'
import formatDate from '../../utils/date'

RecentPost.propTypes = {
    entry: PropTypes.object,
    user: PropTypes.object,
}

export default function RecentPost(props) {
    const { entry, user } = props

    const { data, isLoading, isError } = useQuery(`fish-data-${entry.id}`, () =>
        fetchData(`/fish/info/${entry.fishId}`)
    )

    if (isLoading) return <div></div>
    if (isError) return <div></div>

    const creationDate = formatDate(entry.createdOn)
    const catchDate = formatDate(entry.catchDate)

    return (
        <div className={styles.main_container}>
            <div className={styles.rp_top}>
                <div className={styles.top_left}>
                    <div className={styles.row}>
                        <ProfilePicture state="small" image={user.imgUrl} />
                        <div className={styles.text}>
                            <h2>{user.username}</h2>
                            <p>Gepostet am {creationDate}</p>
                            <p>{entry.description}</p>
                            <hr />
                        </div>
                    </div>
                </div>
            </div>

            <div className={styles.rp_bottom}>
                <div className={styles.bottom_left}>
                    <img id={styles.prevImg} src={entry.imgUrl} />
                </div>
                <div className={styles.bottom_right}>
                    <h2>{data.name}</h2>
                    <p>erfolgreich gefangen!</p>
                    <br />
                    <p>Gewicht: {entry.weight}kg</p>
                    <p>Länge: {entry.length}cm</p>
                    <br />
                    <p>Ort: {entry.location}</p>
                    <p>Datum: {catchDate}</p>
                </div>
            </div>
        </div>
    )
}
