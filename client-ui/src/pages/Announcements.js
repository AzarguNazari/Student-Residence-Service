import React, { useEffect, useState, Fragment } from "react";
import api from "../api";
import { format } from "date-fns";
import Rodal from "rodal";
import NewAnnouncement from "../components/new_announcement";

import "./Announcements.css";
import './page.css';
import LOCAL_STORAGE from "../local-storage";
import Reply from "../components/reply";
import history from "../context/history";
import AnnouncementFilters from "../components/AnnouncementFilters";
import ReactPaginate from "react-paginate";

const Announcements = () => {

    const [announcements, setAnnouncements] = useState([]);
    const [isVisible, setVisible] = useState(false);
    const [type, setType] = useState("");
    const [reply, setReply] = useState({ "user": { "id": 2 } });
    const [selectedAnnouncement, setSelectedAnnouncement] = useState();
    const [replies, setReplies] = useState([]);
    const [pagination, setPagination] = useState({});

    const getAnnouncements = () => {
        api.get("http://localhost:9852/api/v1/bulletinboard", { params: { pageNumber: 0 } })
            .then(({ data }) => {
                const paginationObj = {
                    pageNumber: data['pageNumber'],
                    totalPages: data['totalPages'],
                    totalElements: data['totalElements']
                }
                setPagination(paginationObj);
                setAnnouncements(data['announcements']);
            });
    }

    const getDate = (timestamp) =>
        format(new Date(Date.parse(timestamp)), "dd/MM/yyyy");

    const showDialog = () => {
        setVisible(true);
    }

    const hideDialog = () => {
        setVisible(false);
    }

    const addAnnouncementAction = () => {
        setType("new");
        showDialog();
    }

    useEffect(() => {
        getAnnouncements();
    }, []);

    const addAnnouncement = (announcement) => {
        api.post("http://localhost:9852/api/v1/bulletinboard", announcement)
            .then(() => {
                hideDialog();
                getAnnouncements();
            })
    };

    const getReplies = (announcement) => {
        const announcementId = announcement['external_id'];
        api.get(`http://localhost:9852/api/v1/bulletinboard/${announcementId}/reply`)
            .then(({ data }) => {
                setReplies(data);
            })
    }

    const replyAction = (announcement) => {
        setType("reply");
        showDialog();
        setSelectedAnnouncement(announcement)
        const updatedReply = {
            ...reply,
            announcement: {
                "external_id": announcement['external_id']
            }
        }

        setReply(updatedReply);
    };

    const updateMessage = (text) => {
        const updatedReply = {
            ...reply,
            "message_text": text
        };

        setReply(updatedReply);
    }

    const postReply = (announcementId) => {
        api.post(`http://localhost:9852/api/v1/bulletinboard/${announcementId}/reply`, reply)
            .then(() => {
                hideDialog();
                getAnnouncements();
            })
    }

    function logOut() {
        LOCAL_STORAGE.clearToken();
        history.push('/');
    }

    const selectFilters = () => {
        setType("filter");
        showDialog();
    }

    const applyFilter = (filter) => {
        api.get("http://localhost:9852/api/v1/bulletinboard", { params: filter })
            .then(({ data }) => {
                const paginationObj = {
                    pageNumber: data['pageNumber'],
                    totalPages: data['totalPages'],
                    totalElements: data['totalElements']
                }
                setPagination(paginationObj);
                setAnnouncements(data['announcements']);
            });
    }

    const onPageChange = ({ selected: pageNumber }) => {
        api.get("http://localhost:9852/api/v1/bulletinboard", { params: { pageNumber } })
            .then(({ data }) => {
                const paginationObj = {
                    pageNumber: data['pageNumber'],
                    totalPages: data['totalPages'],
                    totalElements: data['totalElements']
                }
                setPagination(paginationObj);
                setAnnouncements(data['announcements']);
            });
    }

    return (
        <div className="page">
            <h2>Announcements</h2>

            <button className="btn btn-medium btn-medium-a" onClick={selectFilters}>Filters</button>

            {announcements.map(({ announcement }) =>
                <div className="announcement">
                    <h2>{announcement && announcement['description']}</h2>
                    <span className="announced-by">Announcement by: {announcement && announcement['user']['first_name'] + " " + announcement['user']['last_name']}</span>
                    <span className="announcement-type">Announcement Type: {announcement && announcement['announcement_type'].name}</span>
                    <span className={`priority priority-${announcement['priority']}`}>Priority: {announcement && announcement['priority']}</span>
                    <span className="announced-on">Announced On: {announcement && getDate(announcement['creation_date'])}</span>

                    {replies.length > 0 && <div className="replies">
                        {replies.map((replyItem) => {
                            return announcement['external_id'] === replyItem['announcement']['external_id'] && (
                                <div className="reply">
                                    <span className="reply-person">{replyItem && replyItem['user']['first_name']} Replied:</span>
                                    <span className="reply-message">{replyItem && replyItem['message_text']}</span>
                                    <span className="reply-date">Replied at : {replyItem && getDate(replyItem['creation_date'])}</span>
                                </div>
                            )
                        })}
                    </div>}

                    <div>
                        <button className="btn btn-medium btn-medium-a" onClick={() => replyAction(announcement)}>Reply</button>
                        <button className="btn btn-medium btn-medium-a" onClick={() => getReplies(announcement)}>Get Replies</button>
                    </div>
                </div>
            )}
            <ReactPaginate
                previousLabel="<<"
                nextLabel=">>"
                containerClassName="pagination"
                pageCount={pagination['totalPages']}
                pageRangeDisplayed={2}
                marginPagesDisplayed={1}
                onPageChange={onPageChange}
            />
            {LOCAL_STORAGE.getRole() === "ROLE_ADMIN" && <button className="btn btn-medium btn-medium-a" onClick={addAnnouncementAction}>Add an announcement</button>}

            <Rodal visible={isVisible} onClose={hideDialog} closeOnEsc={true} width={500} height={400}>
                {type === "new" && <NewAnnouncement onAddAnnouncement={addAnnouncement} />}
                {type === "reply" && <Reply updateMessage={updateMessage} postReply={postReply} announcement={selectedAnnouncement} />}
                {type === "filter" && <AnnouncementFilters applyFilter={applyFilter} />}
            </Rodal>
            <button className="btn btn-medium btn-medium-a btn-medium-a-red" onClick={logOut}>Log out</button>
        </div>
    )
};

export default Announcements;